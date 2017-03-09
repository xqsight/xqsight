package com.xqsight.common.upload.controller;

import com.xqsight.common.upload.FileUploadException;
import com.xqsight.common.upload.file.*;
import com.xqsight.common.upload.handler.FileHandler;
import com.xqsight.common.upload.handler.LocalFileHandler;
import com.xqsight.common.upload.service.PathResolver;
import com.xqsight.common.upload.support.UploadSupport;
import com.xqsight.common.upload.support.Validations;
import com.xqsight.common.web.Servlets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * WebFileControllerAbstractor
 *
 * @author wangganggang
 */
public abstract class WebFileControllerAbstractor {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected PathResolver pathResolver;

    @Autowired
    protected UploadSupport uploadSupport;

    public static final int TEMPLATE = 1;
    public static final int UPLOAD = 2;

    public static final CommonFileFilter dirFilter = new DirCommonFileFilter();

    protected abstract int getType();

    protected abstract String getBase();

    protected abstract String getDefPath();

    protected abstract String getUrlPrefix();

    protected abstract FileHandler getFileHandler();

    protected abstract boolean isFtp();

    protected String left(HttpServletRequest request, HttpServletResponse response, Model modelMap) throws IOException {
        String base = getBase();
        String urlPrefix = getUrlPrefix();
        FileHandler fileHandler = getFileHandler();
        CommonFile parent = new CommonFile(base, true);
        List<CommonFile> list = fileHandler.listFiles(dirFilter, base, urlPrefix);
        modelMap.addAttribute("parent", parent);
        modelMap.addAttribute("list", list);
        modelMap.addAttribute("type", getType());
        modelMap.addAttribute("isFtp", isFtp());
        return "core/web_file/web_file_left";
    }

    protected String leftTree(HttpServletRequest request, HttpServletResponse response, Model modelMap) throws IOException {
        String parentId = request.getParameter("parentId");
        parentId = parentId == null ? "" : parentId;
        String base = getBase();
        String urlPrefix = getUrlPrefix();
        if (StringUtils.isBlank(parentId)) {
            parentId = base;
        }
        if (!Validations.uri(parentId, base)) {
            throw new FileUploadException("invalidURI");
        }
        FileHandler fileHandler = getFileHandler();
        List<CommonFile> list = fileHandler.listFiles(dirFilter, parentId, urlPrefix);
        modelMap.addAttribute("list", list);
        modelMap.addAttribute("type", getType());
        modelMap.addAttribute("isFtp", isFtp());
        return "core/web_file/web_file_left_tree";
    }

    protected String list(HttpServletRequest request, HttpServletResponse response, Model modelMap) throws IOException {
        String parentId = request.getParameter("parentId");
        parentId = parentId == null ? "" : parentId;
        String searchName = request.getParameter("search_name");
        String base = getBase();
        String defPath = getDefPath();

        String urlPrefix = getUrlPrefix();
        if (StringUtils.isBlank(parentId)) {
            parentId = defPath;
        }
        if (!Validations.uri(parentId, base)) {
            throw new FileUploadException("invalidURI");
        }
        FileHandler fileHandler = getFileHandler();
        CommonFile pp = null;
        if (parentId.length() > base.length()) {
            pp = new CommonFile(CommonFile.getParent(parentId), true);
        }
        List<CommonFile> list = fileHandler.listFiles(searchName, parentId,
                urlPrefix);
        String sort = request.getParameter("page_sort");
        String directionection = request.getParameter("page_sort_dir");
        CommonFile.sort(list, sort, directionection);
        if (pp != null) {
            pp.setParent(true);
            list.add(0, pp);
            modelMap.addAttribute("ppId", pp.getId());
        }
        modelMap.addAttribute("parentId", parentId);
        modelMap.addAttribute("list", list);
        modelMap.addAttribute("type", getType());
        modelMap.addAttribute("isFtp", isFtp());
        return "core/web_file/web_file_list";
    }

    protected String create(HttpServletRequest request, HttpServletResponse response, Model modelMap) throws IOException {
        String parentId = request.getParameter("parentId");
        String base = getBase();
        if (StringUtils.isBlank(parentId)) {
            parentId = base;
        }
        if (!Validations.uri(parentId, base)) {
            throw new FileUploadException("invalidURI");
        }
        String cid = request.getParameter("cid");
        if (StringUtils.isNotBlank(cid)) {
            if (!Validations.uri(cid, base)) {
                throw new FileUploadException("invalidURI");
            }
            FileHandler fileHandler = getFileHandler();

            String urlPrefix = getUrlPrefix();
            CommonFile bean = fileHandler.get(cid, urlPrefix);
            modelMap.addAttribute("bean", bean);
        }

        modelMap.addAttribute("parentId", parentId);
        modelMap.addAttribute("type", getType());
        modelMap.addAttribute("isFtp", isFtp());
        return "core/web_file/web_file_form";
    }

    protected String edit(HttpServletRequest request, HttpServletResponse response, Model modelMap) throws IOException {
        String id = request.getParameter("id");
        String base = getBase();

        String urlPrefix = getUrlPrefix();
        if (!Validations.uri(id, base)) {
            throw new FileUploadException("invalidURI");
        }
        FileHandler fileHandler = getFileHandler();
        CommonFile bean = fileHandler.get(id, urlPrefix);

        String parentId = request.getParameter("parentId");
        modelMap.addAttribute("parentId", parentId);
        modelMap.addAttribute("bean", bean);
        modelMap.addAttribute("type", getType());
        modelMap.addAttribute("isFtp", isFtp());
        return "core/web_file/web_file_form";
    }

    protected String mkdir(String parentId, String dir, HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) throws IOException {
        parentId = parentId == null ? "" : parentId;
        String base = getBase();

        if (StringUtils.isBlank(parentId)) {
            parentId = base;
        }
        if (!Validations.uri(parentId, base)) {
            throw new FileUploadException("invalidURI");
        }
        FileHandler fileHandler = getFileHandler();
        boolean success = fileHandler.mkdir(dir, parentId);
        if (success) {
            logger.info("mkdir files, name={}.", parentId + "/" + dir);
        }
        ra.addFlashAttribute("refreshLeft", true);
        ra.addAttribute("parentId", parentId);
        return "redirect:list.do";
    }

    protected String save(String parentId, String name, String text,
                          String redirect, HttpServletRequest request,
                          HttpServletResponse response, RedirectAttributes ra)
            throws IOException {
        String base = getBase();

        if (!Validations.uri(parentId, base)) {
            throw new FileUploadException("invalidURI");
        }
        FileHandler fileHandler = getFileHandler();
        fileHandler.store(text, name, parentId);
        logger.info("save files, name={}.", parentId + "/" + name);
        ra.addFlashAttribute("refreshLeft", true);
        ra.addAttribute("parentId", parentId);
        return "redirect:edit.do";
    }

    protected void update(String parentId, String origName, String name,
                          String text, Integer position, String redirect,
                          HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String base = getBase();

        if (!Validations.uri(parentId + "/" + origName, base)) {
            throw new FileUploadException("invalidURI");
        }

        FileHandler fileHandler = getFileHandler();
        if (StringUtils.isNotBlank(text)) {
            fileHandler.store(text, origName, parentId);
        }
        if (!origName.equals(name)) {
            fileHandler.rename(name, parentId + "/" + origName);
        }
        logger.info("update files, name={}.", parentId + "/" + origName);
        Servlets.writeHtml(response, "true");
    }

    protected String delete(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) throws IOException {
        String[] ids = Servlets.getParamValues(request, "ids");
        String base = getBase();

        FileHandler fileHandler = getFileHandler();
        for (int i = 0, len = ids.length; i < len; i++) {
            if (!Validations.uri(ids[i], base)) {
                throw new FileUploadException("invalidURI");
            }
        }
        boolean success = fileHandler.delete(ids);
        if (success) {
            for (String id : ids) {

            }
            logger.info("delete files success, name={}.",
                    StringUtils.join(ids, ','));
        } else {
            logger.info("delete files failure, name={}.",
                    StringUtils.join(ids, ','));
        }
        String parentId = Servlets.getParam(request, "parentId");
        ra.addAttribute("parentId", parentId);
        ra.addFlashAttribute("refreshLeft", true);
        return "redirect:list.do";
    }

    protected String rename(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) throws IOException {
        String id = Servlets.getParam(request, "id");
        String name = Servlets.getParam(request, "name");
        String base = getBase();

        FileHandler fileHandler = getFileHandler();
        if (!Validations.uri(id, base)) {
            throw new FileUploadException("invalidURI");
        }
        boolean success = fileHandler.rename(name, id);
        if (success) {
            logger.info("rename files success, name={}.", id);
        }
        String parentId = Servlets.getParam(request, "parentId");
        ra.addAttribute("parentId", parentId);
        ra.addFlashAttribute("refreshLeft", true);
        return "redirect:list.do";
    }

    protected String move(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) throws IOException {
        String[] ids = Servlets.getParamValues(request, "ids");
        String dest = Servlets.getParam(request, "dest");
        String base = getBase();

        if (!Validations.uri(dest, base)) {
            throw new FileUploadException("invalidURI");
        }
        for (int i = 0, len = ids.length; i < len; i++) {
            if (!Validations.uri(ids[i], base)) {
                throw new FileUploadException("invalidURI");
            }
        }
        FileHandler fileHandler = getFileHandler();
        fileHandler.move(dest, ids);
        for (String id : ids) {

        }
        logger.info("move files success, name={}.", StringUtils.join(ids, ','));

        String parentId = Servlets.getParam(request, "parentId");
        ra.addAttribute("parentId", parentId);
        ra.addFlashAttribute("refreshLeft", true);
        return "redirect:list.do";
    }

    protected String zip(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) throws IOException {
        FileHandler fileHandler = getFileHandler();
        if (!(fileHandler instanceof LocalFileHandler)) {
            throw new FileUploadException("ftp cannot support ZIP.");
        }
        LocalFileHandler localFileHandler = (LocalFileHandler) fileHandler;
        String parentId = Servlets.getParam(request, "parentId");
        String[] ids = Servlets.getParamValues(request, "ids");
        String base = getBase();

        File[] files = new File[ids.length];
        for (int i = 0, len = ids.length; i < len; i++) {
            if (!Validations.uri(ids[i], base)) {
                throw new FileUploadException("invalidURI");
            }
            files[i] = localFileHandler.getFile(ids[i]);
        }
        AntZipUtils.zip(files);
        ra.addAttribute("parentId", parentId);
        ra.addFlashAttribute("refreshLeft", true);
        return "redirect:list.do";
    }

    protected void zipDownload(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) throws IOException {
        FileHandler fileHandler = getFileHandler();
        if (!(fileHandler instanceof LocalFileHandler)) {
            throw new FileUploadException("ftp cannot support ZIP.");
        }
        LocalFileHandler localFileHandler = (LocalFileHandler) fileHandler;

        String[] ids = Servlets.getParamValues(request, "ids");
        String base = getBase();

        File[] files = new File[ids.length];
        for (int i = 0, len = ids.length; i < len; i++) {
            if (!Validations.uri(ids[i], base)) {
                throw new FileUploadException("invalidURI");
            }
            files[i] = localFileHandler.getFile(ids[i]);
        }
        response.setContentType("application/x-download;charset=UTF-8");
        response.addHeader("Content-disposition", "filename=download_files.zip");
        try {
            AntZipUtils.zip(files, response.getOutputStream());
        } catch (IOException e) {
            logger.error("zip error!", e);
        }
    }

    protected String unzip(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) throws IOException {
        FileHandler fileHandler = getFileHandler();
        if (!(fileHandler instanceof LocalFileHandler)) {
            throw new FileUploadException("ftp cannot support ZIP.");
        }
        LocalFileHandler localFileHandler = (LocalFileHandler) fileHandler;

        String base = getBase();
        String[] ids = Servlets.getParamValues(request, "ids");
        for (int i = 0, len = ids.length; i < len; i++) {
            if (!Validations.uri(ids[i], base)) {
                throw new FileUploadException("invalidURI");
            }
            File file = localFileHandler.getFile(ids[i]);
            if (AntZipUtils.isZipFile(file)) {
                AntZipUtils.unzip(file, file.getParentFile());
                logger.info("unzip files, name={}.", ids[i]);
            }
        }

        String parentId = Servlets.getParam(request, "parentId");
        ra.addAttribute("parentId", parentId);
        ra.addFlashAttribute("refreshLeft", true);
        return "redirect:list.do";
    }

    protected void upload(@RequestParam(value = "file", required = false) MultipartFile file,
                          HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
        String parentId = Servlets.getParam(request, "parentId");
        String base = getBase();
        if (!Validations.uri(parentId, base)) {
            throw new FileUploadException("invalidURI", parentId);
        }
        FileHandler fileHandler = getFileHandler();
        fileHandler.store(file, parentId);
        logger.info("upload files, name={}.", parentId + "/" + file.getOriginalFilename());
        Servlets.writeHtml(response, "true");
    }

    protected void zipUpload(@RequestParam(value = "file", required = false) MultipartFile file,
                             HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) throws IOException {
        FileHandler fileHandler = getFileHandler();
        if (!(fileHandler instanceof LocalFileHandler)) {
            throw new FileUploadException("ftp cannot support ZIP.");
        }
        LocalFileHandler localFileHandler = (LocalFileHandler) fileHandler;

        String parentId = Servlets.getParam(request, "parentId");
        String base = getBase();
        // parentId = parentId == null ? base : parentId;

        if (!Validations.uri(parentId, base)) {
            throw new FileUploadException("invalidURI");
        }
        File parentFile = localFileHandler.getFile(parentId);
        File tempFile = FilesEx.getTempFile();
        file.transferTo(tempFile);
        AntZipUtils.unzip(tempFile, parentFile);
        tempFile.delete();

        logger.info("zip upload files, name={}.", parentId + "/" + file.getOriginalFilename());
        Servlets.writeHtml(response, "true");
    }

    protected String dir(HttpServletRequest request, HttpServletResponse response, Model modelMap) throws IOException {
        String parentId = Servlets.getParam(request, "parentId");
        parentId = parentId == null ? "" : parentId;
        String base = getBase();
        String urlPrefix = getUrlPrefix();
        if (StringUtils.isBlank(parentId)) {
            parentId = base;
        }
        if (!Validations.uri(parentId, base)) {
            throw new FileUploadException("invalidURI");
        }

        // 需排除的文件夹
        final String[] ids = Servlets.getParamValues(request, "ids");
        CommonFileFilter filter = new CommonFileFilter() {
            public boolean accept(CommonFile file) {
                // 只显示文件夹，不显示文件
                if (file.isDirectory()) {
                    String id = file.getId();
                    for (int i = 0, len = ids.length; i < len; i++) {
                        if (id.equals(ids[i]) || id.startsWith(ids[i] + "/")) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
        };
        FileHandler fileHandler = getFileHandler();
        List<CommonFile> list = fileHandler.listFiles(filter, parentId,
                urlPrefix);
        // 设置当前目录
        CommonFile parent = new CommonFile(parentId, true);
        parent.setCurrent(true);
        list.add(0, parent);
        // 设置上级目录
        if (parentId.length() > base.length()) {
            CommonFile pp = new CommonFile(CommonFile.getParent(parentId), true);
            pp.setParent(true);
            list.add(0, pp);
            modelMap.addAttribute("ppId", pp.getId());
        }
        modelMap.addAttribute("ids", ids);
        modelMap.addAttribute("parentId", parentId);
        modelMap.addAttribute("list", list);
        Servlets.setNoCacheHeader(response);
        return "core/web_file/choose_dir";
    }

    protected String dirList(HttpServletRequest request, HttpServletResponse response, Model modelMap) throws IOException {
        dir(request, response, modelMap);
        return "core/web_file/choose_dir_list";
    }
}
