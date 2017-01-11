package com.xqsight.common.excel;

import java.util.List;
import java.util.Map;

/**
 * Excel数据对象
 * 
 * @author jerry
 *
 * @since 2016-08-12
 */
public class ExcelData {

	/**
	 * Excel参数元数据对象
	 */
	private Map parametersMap;

	/**
	 * Excel集合元对象
	 */
	private List fieldsList;

	/**
	 * 构造函数
	 * 
	 * @param parametersMap
	 *            元参数对象
	 * @param pList
	 *            集合元对象
	 */
	public ExcelData(Map parametersMap , List pList) {
		setParametersMap(parametersMap);
		setFieldsList(pList);
	}

	public Map getParametersMap() {
		return parametersMap;
	}

	public void setParametersMap(Map parametersMap) {
		this.parametersMap = parametersMap;
	}

	public List getFieldsList() {
		return fieldsList;
	}

	public void setFieldsList(List fieldsList) {
		this.fieldsList = fieldsList;
	}
}
