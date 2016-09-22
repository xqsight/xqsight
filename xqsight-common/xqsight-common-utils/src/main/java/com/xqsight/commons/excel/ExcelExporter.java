package com.xqsight.commons.excel;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Excel导出器(适用于WebAPP)
 * 
 * @author jerry
 * @since 2016-08-12
 */
public class ExcelExporter {

	private String templatePath;
	private Map parametersMap;
	private List fieldsList;
	private String filename = "Excel.xls";

	/**
	 * 设置数据
	 * 
	 * @param pMap
	 *            参数集合
	 * @param pList
	 *            字段集合
	 */
	public void setData(Map pMap, List pList) {
		parametersMap = pMap;
		fieldsList = pList;
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
		ExcelData excelData = new ExcelData(parametersMap, fieldsList);
		ExcelTemplate excelTemplate = new ExcelTemplate();
		excelTemplate.setTemplatePath(getTemplatePath());
		excelTemplate.parse();
		ExcelFiller excelFiller = new ExcelFiller(excelTemplate, excelData);
		ByteArrayOutputStream bos = excelFiller.fill();
		ServletOutputStream os = response.getOutputStream();
		os.write(bos.toByteArray());
		os.flush();
		os.close();
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public Map getParametersMap() {
		return parametersMap;
	}

	public void setParametersMap(Map parametersDto) {
		this.parametersMap = parametersDto;
	}

	public List getFieldsList() {
		return fieldsList;
	}

	public void setFieldsList(List fieldsList) {
		this.fieldsList = fieldsList;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
