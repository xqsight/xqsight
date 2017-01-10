package com.xqsight.common.upload;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsFileUploadSupport;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommonsMultipartHandler extends CommonsFileUploadSupport implements ServletContextAware {

    /**
     * Constructor for use as bean. Determines the servlet container's temporary
     * directory via the ServletContext passed in as through the
     * ServletContextAware interface (typically by a WebApplicationContext).
     *
     * @see #setServletContext
     * @see ServletContextAware
     * @see org.springframework.web.context.WebApplicationContext
     */
    public CommonsMultipartHandler() {
        super();
    }

    /**
     * Constructor for standalone usage. Determines the servlet container's
     * temporary directory via the given ServletContext.
     *
     * @param servletContext the ServletContext to use
     */
    public CommonsMultipartHandler(ServletContext servletContext) {
        this();
        setServletContext(servletContext);
    }

    @Override
    protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {
        return new ServletFileUpload(fileItemFactory);
    }

    public void setServletContext(ServletContext servletContext) {
        if (!isUploadTempDirSpecified()) {
            getFileItemFactory().setRepository(
                    WebUtils.getTempDir(servletContext));
        }
    }

    public boolean isMultipart(HttpServletRequest request) {
        return (request != null && ServletFileUpload.isMultipartContent(request));
    }

    public MultipartHttpServletRequest resolveMultipart(final HttpServletRequest request) throws MultipartException {
        Assert.notNull(request, "Request must not be null");
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new RuntimeException("Not Multipart Request");
        }
        MultipartParsingResult parsingResult = parseRequest(request);
        return new DefaultMultipartHttpServletRequest(request,
                parsingResult.getMultipartFiles(),
                parsingResult.getMultipartParameters(),
                parsingResult.getMultipartParameterContentTypes());
    }

    public void cleanupMultipart(MultipartHttpServletRequest request) {
        if (request != null) {
            try {
                cleanupFileItems(request.getMultiFileMap());
            } catch (Throwable ex) {
                logger.warn("Failed to perform multipart cleanup for servlet request", ex);
            }
        }
    }

    /**
     * Parse the given servlet request, resolving its multipart elements.
     *
     * @param request the request to parse
     * @return the parsing result
     * @throws MultipartException if multipart resolution failed.
     */
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        String encoding = determineEncoding(request);
        FileUpload fileUpload = prepareFileUpload(encoding);
        try {
            List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
            return parseFileItems(fileItems, encoding);
        } catch (FileUploadBase.SizeLimitExceededException ex) {
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(),
                    ex);
        } catch (FileUploadException ex) {
            throw new MultipartException(
                    "Could not parse multipart servlet request", ex);
        }
    }

    /**
     * Determine the encoding for the given request. Can be overridden in
     * subclasses.
     * <p>
     * The default implementation checks the request encoding, falling back to
     * the default encoding specified for this resolver.
     *
     * @param request current HTTP request
     * @return the encoding for the request (never {@code null})
     * @see javax.servlet.ServletRequest#getCharacterEncoding
     * @see #setDefaultEncoding
     */
    protected String determineEncoding(HttpServletRequest request) {
        String encoding = request.getCharacterEncoding();
        if (encoding == null) {
            encoding = getDefaultEncoding();
        }
        return encoding;
    }

}
