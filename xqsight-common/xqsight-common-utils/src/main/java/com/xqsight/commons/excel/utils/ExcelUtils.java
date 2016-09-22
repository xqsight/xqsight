package com.xqsight.commons.excel.utils;

import com.xqsight.commons.excel.ExcelData;
import com.xqsight.commons.excel.ExcelExporter;
import com.xqsight.commons.excel.ExcelSupport;
import com.xqsight.commons.excel.ExcelTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelUtils extends ExcelExporter {

    private String filename = "Excel.xls";
    private String sheetName = "";
    private List list = new ArrayList();
    private Boolean[] customStyle;// 用户自定义样式：false表示默认；true表示使用自定义的样式
    private boolean multiParamTemplate = false; // 是否使用多个sheet来定义样本
    private int paramSheetNums = 1; // 是否样本sheet个数

    public ExcelUtils() { }

    public ExcelUtils(List<Map> list) {
        this.list = list;
    }

    /**
     * 导出Excel
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        filename = ExcelSupport.encodeChineseDownloadFileName(request, getFilename());
        response.setHeader("Content-Disposition", "attachment; filename=" + filename + ";");

        ExcelTemplate excelTemplate = new ExcelTemplate();
        excelTemplate.setTemplatePath(getTemplatePath());
        excelTemplate.setMultiParamTemplate(multiParamTemplate);
        excelTemplate.setParamSheetNums(paramSheetNums);
        excelTemplate.parse();

        // ExcelFiller excelFiller = new ExcelFiller(excelTemplate, excelData);
        ExcelFillerUtils excelFillerUtils = new ExcelFillerUtils(excelTemplate);
        ByteArrayOutputStream bos = null;
        ServletOutputStream os = null;
        for (int i = 0; i < this.list.size(); i++) {
            Map map = (Map) list.get(i);
            Map dto = (Map) map.get("parametersMap");
            List fList = (List) map.get("fieldsList");
            ExcelData excelData = new ExcelData(dto, fList);
            excelFillerUtils.setExcelData(excelData);
            // ByteArrayOutputStream bos = excelFiller.fill();
            if (getSheetName() != null && !"".equals(getSheetName())) {
                excelFillerUtils.setSheetName(getSheetName());
            }
            if (getCustomStyle(i) != null) {
                excelFillerUtils.setCustomStyle(getCustomStyle(i));
            }
            excelFillerUtils.fill(i);
        }
        excelFillerUtils.write();
        bos = excelFillerUtils.getByteArrayOutputStream();
        os = response.getOutputStream();
        os.write(bos.toByteArray());
        os.flush();
        os.close();
    }

    /**
     * 导出Excel
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void export(HttpServletRequest request, HttpServletResponse response, ExcelTemplate excelTemplate) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        filename = ExcelSupport.encodeChineseDownloadFileName(request, getFilename());
        response.setHeader("Content-Disposition", "attachment; filename=" + filename + ";");

        excelTemplate.setTemplatePath(getTemplatePath());
        excelTemplate.setMultiParamTemplate(multiParamTemplate);
        excelTemplate.setParamSheetNums(paramSheetNums);
        excelTemplate.parse();

        // ExcelFiller excelFiller = new ExcelFiller(excelTemplate, excelData);
        ExcelFillerUtils excelFillerUtils = new ExcelFillerUtils(excelTemplate);
        ByteArrayOutputStream bos = null;
        ServletOutputStream os = null;
        for (int i = 0; i < this.list.size(); i++) {
            Map map = (Map) list.get(i);
            Map dto = (Map) map.get("parametersMap");
            List fList = (List) map.get("fieldsList");
            ExcelData excelData = new ExcelData(dto, fList);
            excelFillerUtils.setExcelData(excelData);
            // ByteArrayOutputStream bos = excelFiller.fill();
            if (getSheetName() != null && !"".equals(getSheetName())) {
                excelFillerUtils.setSheetName(getSheetName());
            }
            if (getCustomStyle(i) != null) {
                excelFillerUtils.setCustomStyle(getCustomStyle(i));
            }

            excelFillerUtils.fill(i + excelTemplate.getSkipSheets());
        }
        //删除多余的sheet row column
        if (excelTemplate.getCleanTheDoc() != null) {
            excelFillerUtils.cleanTheDoc(excelTemplate.getCleanTheDoc());
        }
        excelFillerUtils.write();
        bos = excelFillerUtils.getByteArrayOutputStream();
        os = response.getOutputStream();
        os.write(bos.toByteArray());
        os.flush();
        os.close();
    }

    /**
     * @return the sheetName
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * @param sheetName the sheetName to set
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Boolean getCustomStyle(int i) {
        return this.customStyle == null || this.customStyle.length == 0 ? null : this.customStyle[i];
    }

    public Boolean[] getCustomStyle() {
        return this.customStyle;
    }

    public void setCustomStyle(Boolean[] customStyle) {
        this.customStyle = customStyle;
    }

    public void setCustomStyle(int i, boolean customStyle) {
        this.customStyle[i] = customStyle;
    }

    public boolean isMultiParamTemplate() {
        return multiParamTemplate;
    }

    public void setMultiParamTemplate(boolean multiParamTemplate) {
        this.multiParamTemplate = multiParamTemplate;
    }

    public int getParamSheetNums() {
        return paramSheetNums;
    }

    public void setParamSheetNums(int paramSheetNums) {
        this.paramSheetNums = paramSheetNums;
    }

}
