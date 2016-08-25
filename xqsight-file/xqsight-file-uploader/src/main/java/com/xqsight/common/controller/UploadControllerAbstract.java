package com.xqsight.common.controller;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.GlobalUpload;
import com.xqsight.common.file.FileHandler;
import com.xqsight.common.file.LocalFileHandler;
import com.xqsight.common.support.UploadHandler;
import com.xqsight.common.upload.UploadResult;
import com.xqsight.common.upload.Uploader;
import com.xqsight.common.web.PathResolver;
import com.xqsight.commons.web.WebUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;

/**
 * UploadControllerAbstract
 *
 * @author liufang
 *
 */
public abstract class UploadControllerAbstract {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected MessageSource messageSource;
	@Autowired
	protected PathResolver pathResolver;
	@Autowired
	protected UploadHandler uploadHandler;


	protected void imageSavePath(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("fetch") != null) {
			response.setHeader("Content-Type", "text/javascript");
			response.getWriter().print("updateSavePath( ['image'] );");
			return;
		}
	}

	protected void imageManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().print("");
	}

	protected void getMovie(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuffer readOneLineBuff = new StringBuffer();
		String content = "";
		String searchkey = request.getParameter("searchKey");
		String videotype = request.getParameter("videoType");
		try {
			searchkey = URLEncoder.encode(searchkey, "utf-8");
			URL url = new URL(
					"http://api.tudou.com/v3/gw?method=item.search&appKey=myKey&format=json&kw="
							+ searchkey + "&pageNo=1&pageSize=20&channelId="
							+ videotype + "&inDays=7&media=v&sort=s");
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader( conn.getInputStream(), "utf-8"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				readOneLineBuff.append(line);
			}
			content = readOneLineBuff.toString();
			reader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		response.getWriter().print(content);
	}

	protected void getRemoteImage(String upfile, HttpServletRequest request, HttpServletResponse response) throws IOException {
		GlobalUpload gu = new GlobalUpload();
		String prefix = "";
		FileHandler fileHandler = new LocalFileHandler(pathResolver,prefix);

		String state = "SUCCESS";
		String[] arr = upfile.split("ue_separate_ue");
		List<String> urls = new ArrayList<String>();
		List<String> srcs = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			String extension = FilenameUtils.getExtension(arr[i]);
			// 格式验证
			if (!gu.isExtensionValid(extension, Uploader.IMAGE)) {
				state = "Extension Invalid";
				continue;
			}
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection conn = (HttpURLConnection) new URL(arr[i])
					.openConnection();
			if (conn.getContentType().indexOf("image") == -1) {
				state = "ContentType Invalid";
				continue;
			}
			if (conn.getResponseCode() != 200) {
				state = "Request Error";
				continue;
			}
			String urlPrefix = ""; //TODO
			String pathname = Uploader.getQuickPathname(Uploader.IMAGE, extension);
			fileHandler.storeFile(conn.getInputStream(), pathname);
			urls.add(urlPrefix + pathname);
			srcs.add(arr[i]);
		}
		StringBuilder outstr = new StringBuilder();
		for (String url : urls) {
			outstr.append(url).append("ue_separate_ue");
		}
		StringBuilder srcUrl = new StringBuilder();
		for (String src : srcs) {
			srcUrl.append(src).append("ue_separate_ue");
		}
		int sepLength = "ue_separate_ue".length();
		if (outstr.length() > sepLength) {
			outstr.setLength(outstr.length() - sepLength);
		}
		if (srcUrl.length() > sepLength) {
			srcUrl.setLength(srcUrl.length() - sepLength);
		}
		response.getWriter().print(
				"{'url':'" + outstr + "','tip':'" + state + "','srcUrl':'"
						+ srcUrl + "'}");
	}

	protected void upload(HttpServletRequest request, HttpServletResponse response, String type) throws IOException {
		upload(request, response, type, null, null, null, null, null, null, null, null);
	}

	protected void upload(HttpServletRequest request,
			HttpServletResponse response, String type, Boolean scale,
			Boolean exact, Integer width, Integer height, Boolean thumbnail,
			Integer thumbnailWidth, Integer thumbnailHeight, Boolean watermark)
			throws IOException {
		UploadResult result = new UploadResult();
		Locale locale = RequestContextUtils.getLocale(request);
		result.setMessageSource(messageSource, locale);

		Integer userId = 1 ;//Context.getCurrentUserId();
		String ip = WebUtils.getUserIp(request);
		MultipartFile partFile = getMultipartFile(request);
		uploadHandler.upload(partFile, type, userId, ip, result, scale,
				exact, width, height, thumbnail, thumbnailWidth,
				thumbnailHeight, watermark);

		if (request.getParameter("CKEditor") != null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String callback = request.getParameter("CKEditorFuncNum");
			out.println("<script type=\"text/javascript\">");
			out.println("(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n");
			if (result.isError()) {
				out.println("window.parent.CKEDITOR.tools.callFunction("
						+ callback + ",'" + result.getFileUrl() + "',''" + ");");
			} else {
				out.println("alert('" + result.getMessage() + "');");
			}
			out.print("</script>");
			out.flush();
			out.close();
		} else if (request.getParameter("ueditor") != null) {
			Map<String, String> umap = new HashMap<String, String>();
			String title = request.getParameter("pictitle");
			umap.put("title", title);
			umap.put("state", "SUCCESS");
			umap.put("original", result.getFileName());
			umap.put("url", result.getFileUrl());
			umap.put("fileType", "." + result.getFileExtension());

			PrintWriter out = response.getWriter();
			out.write(JSON.toJSONString(umap));
			out.flush();
			out.close();
		} else if (request.getParameter("editormd") != null) {
			// {
			// success : 0 | 1, // 0 表示上传失败，1 表示上传成功
			// message : "提示的信息，上传成功或上传失败及错误信息等。",
			// url : "图片地址" // 上传成功时才返回
			// }
			Map<String, String> umap = new HashMap<String, String>();
			umap.put("success", result.isSuccess() ? "1" : "0");
			umap.put("message", result.getMessage());
			umap.put("url", result.getFileUrl());
			PrintWriter out = response.getWriter();
			out.write(JSON.toJSONString(umap));
			out.flush();
			out.close();
		} else {
			PrintWriter out = response.getWriter();
			out.write(JSON.toJSONString(result));
			out.flush();
			out.close();
		}
	}

	private MultipartFile getMultipartFile(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		if (fileMap.isEmpty()) {
			throw new IllegalStateException("No upload file found!");
		}
		return fileMap.entrySet().iterator().next().getValue();
	}
}
