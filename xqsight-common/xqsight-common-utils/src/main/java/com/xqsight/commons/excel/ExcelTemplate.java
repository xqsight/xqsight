package com.xqsight.commons.excel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 导出Excel的模板对象
 * 
 * @author jerry
 * @since 2016-08-12
 */
public class ExcelTemplate {
	private static Logger logger = LogManager.getLogger(ExcelTemplate.class);

	private List staticObject = null;
	private List parameterObjct = null;
	private List fieldObjct = null;
	private List variableObject = null;
	private String templatePath = null;
	
	private boolean multiParamTemplate = false;
	private int paramSheetNums = 1;
	private int skipSheets = 0;
	private String cleanTheDoc = null;
	private List<HashMap<String, List>> mObjectList = new ArrayList<HashMap<String,List>>();

	public ExcelTemplate(String pTemplatePath) {
		templatePath = pTemplatePath;
	}
	
	public ExcelTemplate() {}
	
	/**
	 * 解析Excel模板
	 */
	public void parse() {

		if(ExcelSupport.isEmpty(templatePath)){
			logger.error("Excel模板路径不能为空!"); return;
		}

		InputStream is = ExcelTemplate.class.getResourceAsStream(templatePath);
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(is);
		} catch (Exception e) {
			logger.error("获取Excel模板异常,原因:{}",e.getMessage());
			e.printStackTrace();
		}

		if(multiParamTemplate){
			for(int i = 0; i < paramSheetNums; i++){
				List sObject = new ArrayList();
				List pObjct = new ArrayList();
				List fObjct = new ArrayList();
				List vObject = new ArrayList();
				Sheet sheet = workbook.getSheet(i + this.skipSheets);
				if (ExcelSupport.isNotEmpty(sheet)) {
					int rows = sheet.getRows();
					for (int k = 0; k < rows; k++) {
						Cell[] cells = sheet.getRow(k);
						for (int j = 0; j < cells.length; j++) {
							String cellContent = cells[j].getContents().trim();
							if (!ExcelSupport.isEmpty(cellContent)) {
								if (cellContent.indexOf("$P") != -1 || cellContent.indexOf("$p") != -1) {
									pObjct.add(cells[j]);
								} else if (cellContent.indexOf("$F") != -1 || cellContent.indexOf("$f") != -1) {
									fObjct.add(cells[j]);
								} else if(cellContent.indexOf("$V") != -1 || cellContent.indexOf("$v") != -1) {
									vObject.add(cells[j]);
								}else {
									sObject.add(cells[j]);
								}
							}
						}
					}
				} else {
					logger.debug("模板工作表对象不能为空!");
				}
				HashMap<String, List> map = new HashMap<String, List>();
				map.put("staticObject", sObject);
				map.put("parameterObjct", pObjct);
				map.put("fieldObjct", fObjct);
				map.put("variableObject", vObject);
				mObjectList.add(map);
			}
		}else{
			staticObject = new ArrayList();
			parameterObjct = new ArrayList();
			fieldObjct = new ArrayList();
			variableObject = new ArrayList();
			Sheet sheet = workbook.getSheet(this.skipSheets);
			if (ExcelSupport.isNotEmpty(sheet)) {
				int rows = sheet.getRows();
				for (int k = 0; k < rows; k++) {
					Cell[] cells = sheet.getRow(k);
					for (int j = 0; j < cells.length; j++) {
						String cellContent = cells[j].getContents().trim();
						if (!ExcelSupport.isEmpty(cellContent)) {
							if (cellContent.indexOf("$P") != -1 || cellContent.indexOf("$p") != -1) {
								parameterObjct.add(cells[j]);
							} else if (cellContent.indexOf("$F") != -1 || cellContent.indexOf("$f") != -1) {
								fieldObjct.add(cells[j]);
							} else if(cellContent.indexOf("$V") != -1 || cellContent.indexOf("$v") != -1) {
								variableObject.add(cells[j]);
							}else {
								staticObject.add(cells[j]);
							}
						}
					}
				}
			} else {
				logger.debug("模板工作表对象不能为空!");
			}
		}
		workbook.close();
	}

	/**
	 * 增加一个静态文本对象
	 */
	public void addStaticObject(Cell cell) {
		staticObject.add(cell);
	}

	/**
	 * 增加一个参数对象
	 */
	public void addParameterObjct(Cell cell) {
		parameterObjct.add(cell);
	}

	/**
	 * 增加一个字段对象
	 */
	public void addFieldObjct(Cell cell) {
		fieldObjct.add(cell);
	}


	public List getStaticObject() {
		return staticObject;
	}
	
	public List getStaticObject(int i) {
		if(this.mObjectList.size() > i){
			staticObject = this.mObjectList.get(i).get("staticObject");
		}
		return staticObject;
	}

	public List getParameterObjct() {
		return parameterObjct;
	}
	
	public List getParameterObjct(int i) {
		if(this.mObjectList.size() > i){
			parameterObjct = this.mObjectList.get(i).get("parameterObjct");
		}
		return parameterObjct;
	}

	public List getFieldObjct() {
		return fieldObjct;
	}
	
	public List getFieldObjct(int i) {
		if(this.mObjectList.size() > i){
			fieldObjct = this.mObjectList.get(i).get("fieldObjct");
		}
		return fieldObjct;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public List getVariableObject() {
		return variableObject;
	}
	
	public List getVariableObject(int i) {
		if(this.mObjectList.size() > i){
			variableObject = this.mObjectList.get(i).get("variableObject");
		}
		return variableObject;
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

	public List<HashMap<String, List>> getmObjectList() {
		return mObjectList;
	}

	public int getSkipSheets() {
		return skipSheets;
	}

	public void setSkipSheets(int skipSheets) {
		this.skipSheets = skipSheets;
	}

	public String getCleanTheDoc() {
		return cleanTheDoc;
	}

	public void setCleanTheDoc(String cleanTheDoc) {
		this.cleanTheDoc = cleanTheDoc;
	}

}
