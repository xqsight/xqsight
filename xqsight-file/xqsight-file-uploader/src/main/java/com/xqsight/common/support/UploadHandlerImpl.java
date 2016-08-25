package com.xqsight.common.support;

import com.xqsight.common.GlobalUpload;
import com.xqsight.common.file.FileHandler;
import com.xqsight.common.file.Files;
import com.xqsight.common.image.Images;
import com.xqsight.common.image.ScaleParam;
import com.xqsight.common.image.ThumbnailParam;
import com.xqsight.common.image.WatermarkParam;
import com.xqsight.common.upload.UploadResult;
import com.xqsight.common.upload.Uploader;
import com.xqsight.common.web.PathResolver;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UploadHandlerImpl implements UploadHandler {

	private static Logger logger = LogManager.getLogger(UploadHandlerImpl.class);

	@Autowired
	protected PathResolver pathResolver;

	public String copyImage(BufferedImage buff, String extension,
			String formatName, Boolean scale, Boolean exact,
			Integer width, Integer height, Boolean thumbnail,
			Integer thumbnailWidth, Integer thumbnailHeight, Boolean watermark,
			String ip, Integer userId) {

		GlobalUpload gu = new GlobalUpload();
		ScaleParam scaleParam = gu.getScaleParam(scale, exact, width, height);
		scale = scaleParam.getScale();

		ThumbnailParam thumbnailParam = new ThumbnailParam(thumbnail, thumbnailWidth, thumbnailHeight);
		thumbnail = thumbnailParam.getThumbnail();


		String urlPrefix = point.getUrlPrefix();
		FileHandler fileHandler = point.getFileHandler(pathResolver);
		String pathname = UploadUtils.getUrl(1000, Uploader.IMAGE,extension);
		try {
			storeImage(buff, scaleParam, thumbnailParam, watermarkParam,
					formatName, pathname, fileHandler, ip, userId, siteId);
			long length = buff.getWidth() * buff.getHeight() / 3;
			attachmentService.save(pathname, length, ip, userId, siteId);
			return urlPrefix + pathname;
		} catch (IOException e) {
			logger.error("", e);
			return null;
		}
	}

	public String storeImage(BufferedImage buff, String extension,String formatName, String ip, Integer userId) {
		PublishPoint point = site.getUploadsPublishPoint();
		FileHandler fileHandler = point.getFileHandler(pathResolver);
		String urlPrefix = point.getUrlPrefix();
		String filename = UploadUtils.getUrl(site.getId(), Uploader.IMAGE,
				extension);
		try {
			fileHandler.storeImage(buff, formatName, filename);
			long length = buff.getWidth() * buff.getHeight() / 3;
			attachmentService.save(filename, length, ip, userId, site.getId());
			return urlPrefix + filename;
		} catch (IOException e) {
			logger.error(null, e);
			return null;
		}
	}

	public void upload(String url, String type, Integer userId,
			String ip, UploadResult result) {
		upload(url, type, userId, ip, result, null, null, null, null,
				null, null, null, null);
	}

	public void upload(MultipartFile partFile, String type,
					   Integer userId, String ip, UploadResult result) {
		upload(partFile, type, userId, ip, result, null, null, null,
				null, null, null, null, null);
	}

	public void upload(String url, String type, Integer userId,
			String ip, UploadResult result, Boolean scale, Boolean exact,
			Integer width, Integer height, Boolean thumbnail,
			Integer thumbnailWidth, Integer thumbnailHeight, Boolean watermark) {
		try {
			URL source = new URL(url);
			// file（下载）支持重定向支持，其他的不支持。
			if (Uploader.FILE.equals(type)) {
				HttpURLConnection.setFollowRedirects(true);
			} else {
				HttpURLConnection.setFollowRedirects(false);
			}
			HttpURLConnection conn = (HttpURLConnection) source
					.openConnection();
			conn.setRequestProperty("User-Agent", Constants.USER_ANGENT);
			int responseCode = conn.getResponseCode();
			if (responseCode != 200) {
				result.setError("URL response error:" + responseCode);
				return;
			}
			if (Uploader.IMAGE.equals(type)) {
				String contentType = conn.getContentType();
				if (!validateImageContentType(contentType, result)) {
					return;
				}
			}
			String disposition = conn
					.getHeaderField(HttpHeaders.CONTENT_DISPOSITION);
			String fileName = StringUtils.substringBetween(disposition,
					"filename=\"", "\"");
			if (StringUtils.isBlank(fileName)) {
				fileName = FilenameUtils.getName(source.getPath());
			}
			String ext = FilenameUtils.getExtension(fileName);
			File temp = Files.getTempFile("." + ext);
			InputStream is = conn.getInputStream();
			FileUtils.copyInputStreamToFile(is, temp);
			IOUtils.closeQuietly(is);
			try {
				doUpload(temp, fileName, type, userId, ip, result, scale,
						exact, width, height, thumbnail, thumbnailWidth,
						thumbnailHeight, watermark);
			} finally {
				FileUtils.deleteQuietly(temp);
			}
		} catch (Exception e) {
			result.setError(e.getMessage());
		}
		return;
	}

	public void upload(MultipartFile partFile, String type,
					   Integer userId, String ip, UploadResult result, Boolean scale,
					   Boolean exact, Integer width, Integer height, Boolean thumbnail,
					   Integer thumbnailWidth, Integer thumbnailHeight, Boolean watermark) {
		try {
			if (!validateFile(partFile, result)) {
				return;
			}
			String fileName = partFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(fileName);
			File temp = Files.getTempFile("." + ext);
			partFile.transferTo(temp);
			try {
				doUpload(temp, fileName, type, userId, ip, result, scale,
						exact, width, height, thumbnail, thumbnailWidth,
						thumbnailHeight, watermark);
			} finally {
				FileUtils.deleteQuietly(temp);
			}
		} catch (Exception e) {
			result.setError(e.getMessage());
			logger.error(null, e);
		}
		return;
	}

	private UploadResult doUpload(File file, String fileName, String type,
			 Integer userId, String ip, UploadResult result,
			Boolean scale, Boolean exact, Integer width, Integer height,
			Boolean thumbnail, Integer thumbnailWidth, Integer thumbnailHeight,
			Boolean watermark) throws Exception {
		Integer siteId = site.getId();
		long fileLength = file.length();
		String ext = FilenameUtils.getExtension(fileName).toLowerCase();
		GlobalUpload gu = site.getGlobal().getUpload();
		// 后缀名是否合法
		if (!validateExt(ext, type, gu, result)) {
			return result;
		}
		// 文库是否开启
		if (type == Uploader.DOC && !isDocEnabled(result, site.getGlobal())) {
			return result;
		}
		PublishPoint point = site.getUploadsPublishPoint();
		String urlPrefix = point.getUrlPrefix();
		FileHandler fileHandler = point.getFileHandler(pathResolver);

		String pathname = site
				.getSiteBase(Uploader.getQuickPathname(type, ext));
		String fileUrl = urlPrefix + pathname;
		String pdfUrl = null;
		String swfUrl = null;
		if (Uploader.IMAGE.equals(type)) {
			SiteWatermark sw = site.getWatermark();
			doUploadImage(fileHandler, file, pathname, scale, exact, width,
					height, thumbnail, thumbnailWidth, thumbnailHeight,
					watermark, gu, sw, ip, userId, siteId);
		} else if (Uploader.DOC == type) {
			if (!"swf".equals(ext)) {
				String swfPathname = site.getSiteBase(Uploader
						.getQuickPathname(type, "swf"));
				swfUrl = urlPrefix + swfPathname;
				String pdfPathname = null;
				if (!"pdf".equals(ext)) {
					pdfPathname = site.getSiteBase(Uploader.getQuickPathname(
							type, "pdf"));
					pdfUrl = urlPrefix + pdfPathname;
				} else {
					pdfUrl = fileUrl;
				}
				UploadDoc.exec(attachmentService, fileHandler, pathname, ext,
						pdfPathname, swfPathname, file, ip, userId, siteId);
			} else {
				swfUrl = fileUrl;
				fileHandler.storeFile(file, pathname);
			}
		} else {
			fileHandler.storeFile(file, pathname);
		}
		attachmentService.save(pathname, fileLength, ip, userId, siteId);
		result.set(fileUrl, fileName, ext, fileLength, pdfUrl, swfUrl);
		return result;
	}

	private void doUploadImage(FileHandler fileHandler, File file,
			String pathname, Boolean scale, Boolean exact, Integer width,
			Integer height, Boolean thumbnail, Integer thumbnailWidth,
			Integer thumbnailHeight, Boolean watermark, GlobalUpload gu,
			SiteWatermark sw, String ip, Integer userId, Integer siteId)
			throws IOException {
		ScaleParam scaleParam = gu.getScaleParam(scale, exact, width, height);
		scale = scaleParam.getScale();

		ThumbnailParam thumbnailParam = new ThumbnailParam(thumbnail,
				thumbnailWidth, thumbnailHeight);
		thumbnail = thumbnailParam.getThumbnail();

		WatermarkParam watermarkParam = sw.getWatermarkParam(watermark);
		watermark = watermarkParam.getWatermark();

		String formatName = null;
		if (watermark || scale || thumbnail) {
			InputStream is = FileUtils.openInputStream(file);
			formatName = Images.getFormatName(is);
			IOUtils.closeQuietly(is);
		}
		if (StringUtils.isNotBlank(formatName)) {
			// 可以且需要处理的图片
			BufferedImage buff = ImageIO.read(file);
			storeImage(buff, scaleParam, thumbnailParam, watermarkParam,
					formatName, pathname, fileHandler, ip, userId, siteId);
		} else {
			// 不可处理的图片
			fileHandler.storeFile(file, pathname);
		}
	}

	private void storeImage(BufferedImage buff, ScaleParam scaleParam,
			ThumbnailParam thumbnailParam, WatermarkParam watermarkParam,
			String formatName, String pathname, FileHandler fileHandler,
			String ip, Integer userId, Integer siteId) throws IOException {
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		List<String> filenames = new ArrayList<String>();
		if (scaleParam.getScale()) {
			buff = Images.resize(buff, scaleParam);
		}
		BufferedImage thumbnailBuff = null;
		String thumbnailName = null;
		if (thumbnailParam.getThumbnail()) {
			Integer width = thumbnailParam.getWidth();
			Integer height = thumbnailParam.getHeight();
			thumbnailBuff = Scalr.resize(buff, Scalr.Method.QUALITY,
					Scalr.Mode.AUTOMATIC, width, height);
			thumbnailName = Uploader.getThumbnailName(pathname);
			images.add(thumbnailBuff);
			filenames.add(thumbnailName);
		}
		if (watermarkParam.getWatermark()) {
			String imagePath = watermarkParam.getImagePath();
			FileHandler handler = FileHandler.getFileHandler(pathResolver,
					Constants.TEMPLATE_STORE_PATH);
			BufferedImage watermarkBuff = handler.readImage(imagePath);
			if (watermarkBuff != null) {
				Images.watermark(buff, watermarkBuff, watermarkParam);
			}
		}
		images.add(buff);
		filenames.add(pathname);
		fileHandler.storeImages(images, formatName, filenames);
		if (thumbnailName != null) {
			long length = thumbnailBuff.getWidth() * thumbnailBuff.getHeight()
					/ 3;
			attachmentService.save(thumbnailName, length, ip, userId, siteId);
		}
	}

	private boolean validateExt(String extension, String type, GlobalUpload gu,
			UploadResult result) {
		if (!gu.isExtensionValid(extension, type)) {
			logger.debug("image extension not allowed: " + extension);
			result.setErrorCode("imageExtensionNotAllowed",
					new String[] { extension });
			return false;
		}
		return true;
	}

	private boolean isDocEnabled(UploadResult result, Global global) {
		if (!global.isDocEnabled()) {
			result.setError("DOC Converter is not available!");
			return false;
		}
		return true;
	}

	private boolean validateFile(MultipartFile partFile, UploadResult result) {
		if (partFile == null || partFile.isEmpty()) {
			logger.debug("file is empty");
			result.setError("no file upload!");
			return false;
		}
		return true;
	}

	private boolean validateImageContentType(String contentType,
			UploadResult result) {
		if (!StringUtils.contains(contentType, "image")) {
			logger.debug("ContentType not contain Image: " + contentType);
			result.setError("ContentType not contain Image: " + contentType);
			return false;
		}
		return true;
	}
}
