package com.xqsight.common.excel;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Excel数据填充器
 * 
 * @author jerry
 * @since 2016-08-12
 */
public class ExcelFiller {
	private static Logger logger = LogManager.getLogger(ExcelFiller.class);

	private ExcelTemplate excelTemplate = null;
	private ExcelData excelData = null;

	public ExcelFiller() {}

	/**
	 * 构造函数
	 * 
	 * @param pExcelTemplate
	 * @param pExcelData
	 */
	public ExcelFiller(ExcelTemplate pExcelTemplate, ExcelData pExcelData) {
		setExcelData(pExcelData);
		setExcelTemplate(pExcelTemplate);
	}

	/**
	 * 数据填充 将ExcelData填入excel模板
	 * 
	 * @return ByteArrayOutputStream
	 */
	public ByteArrayOutputStream fill() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			InputStream is = ExcelFiller.class.getResourceAsStream(getExcelTemplate().getTemplatePath());
			Workbook wb = Workbook.getWorkbook(is);
			WorkbookSettings settings = new WorkbookSettings();
			settings.setWriteAccess(null);
			WritableWorkbook wwb = Workbook.createWorkbook(bos, wb, settings);
			WritableSheet wSheet = wwb.getSheet(0);
			fillStatics(wSheet);
			fillParameters(wSheet);
			fillFields(wSheet);
			wwb.write();
			wwb.close();
			wb.close();
		} catch (Exception e) {
			logger.error("基于模板生成可写工作表出错了,错误信息:{}",e.getMessage());
			e.printStackTrace();
		}
		return bos;
	}

	/**
	 * 写入静态对象
	 */
	private void fillStatics(WritableSheet wSheet) {
		List statics = getExcelTemplate().getStaticObject();
		for (int i = 0; i < statics.size(); i++) {
			Cell cell = (Cell) statics.get(i);
			Label label = new Label(cell.getColumn(), cell.getRow(),cell.getContents());
			label.setCellFormat(cell.getCellFormat());
			try {
				wSheet.addCell(label);
			} catch (Exception e) {
				logger.error("写入静态对象发生错误,错误信息:{}",e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写入参数对象
	 */
	private void fillParameters(WritableSheet wSheet) {
		List parameters = getExcelTemplate().getParameterObjct();
		Map parameterMap = getExcelData().getParametersMap();
		for (int i = 0; i < parameters.size(); i++) {
			Cell cell = (Cell) parameters.get(i);
			String key = getKey(cell.getContents().trim());
			String type = getType(cell.getContents().trim());
			try {
				if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Number)) {
					Number number = new Number(cell.getColumn(), cell.getRow(),MapUtils.getDoubleValue(parameterMap,key));
					number.setCellFormat(getBodyCellStyle());
					wSheet.addCell(number);
				} else {
					Label label = new Label(cell.getColumn(), cell.getRow(),MapUtils.getString(parameterMap,key));
					label.setCellFormat(getBodyCellStyle());
					wSheet.addCell(label);
				}
			} catch (Exception e) {
				logger.error("写入表格参数对象发生错误,错误信息:{}",e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写入表格字段对象
	 * 
	 * @throws Exception
	 */
	private void fillFields(WritableSheet wSheet) throws Exception {
		List fields = getExcelTemplate().getFieldObjct();
		List fieldList = getExcelData().getFieldsList();
		for (int j = 0; j < fieldList.size(); j++) {
			Map dataMap = new HashMap<>();
			Object object = fieldList.get(j);
			if (object instanceof Map<?,?>) {
				Map dto = (Map) object;
				dataMap.putAll(dto);
			} else {
				logger.error("不支持的数据类型!");
			}
			for (int i = 0; i < fields.size(); i++) {
				Cell cell = (Cell) fields.get(i);
				String key = getKey(cell.getContents().trim());
				String type = getType(cell.getContents().trim());
				try {
					if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Number)) {
						Number number = new Number(cell.getColumn(), cell.getRow() + j, MapUtils.getDoubleValue(dataMap,key));
						number.setCellFormat(getBodyCellStyle());
						wSheet.addCell(number);
					} else if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Formual)) {
						Formula formual = new Formula(cell.getColumn(), cell.getRow() + j, key);
						wSheet.addCell(formual);
					} else {
						Label label = new Label(cell.getColumn(), cell.getRow() + j, MapUtils.getString(dataMap,key));
						label.setCellFormat(getBodyCellStyle());
						wSheet.addCell(label);
					}
				} catch (Exception e) {
					logger.error("写入表格字段对象发生错误,错误信息:{}",e.getMessage());
					e.printStackTrace();
				}
			}
		}
		int row = 0;
		row += fieldList.size();
		if (ExcelSupport.isEmpty(fieldList)) {
			if (ExcelSupport.isNotEmpty(fields)) {
				Cell cell = (Cell) fields.get(0);
				row = cell.getRow();
				wSheet.removeRow(row + 5);
				wSheet.removeRow(row + 4);
				wSheet.removeRow(row + 3);
				wSheet.removeRow(row + 2);
				wSheet.removeRow(row + 1);
				wSheet.removeRow(row);
			}
		} else {
			Cell cell = (Cell) fields.get(0);
			row += cell.getRow();
			fillVariables(wSheet, row);
		}
	}

	/**
	 * 写入变量对象
	 */
	private void fillVariables(WritableSheet wSheet, int row) {
		List variables = getExcelTemplate().getVariableObject();
		Map parameterMap = getExcelData().getParametersMap();
		for (int i = 0; i < variables.size(); i++) {
			Cell cell = (Cell) variables.get(i);
			String key = getKey(cell.getContents().trim());
			String type = getType(cell.getContents().trim());
			try {
				if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Number)) {
					Number number = new Number(cell.getColumn(), row,MapUtils.getDoubleValue(parameterMap,key));
					number.setCellFormat(getBodyCellStyle());
					wSheet.addCell(number);
				} else if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Formual)) {
					/*
					 * 转义,$R表示当前行数
					 */
					if (key.indexOf("$R") > -1) {
						key = key.replaceAll("\\$R", String.valueOf(row));
					}
					Formula formual = new Formula(cell.getColumn(), row, key);
					// Formula formual = new Formula(cell.getColumn(),
					// row,"SUM(E4:E4)" );
					formual.setCellFormat(getBodyCellStyle());
					wSheet.addCell(formual);
				} else {
					String content = MapUtils.getString(parameterMap,key);
					if (ExcelSupport.isEmpty(content) && !key.equalsIgnoreCase("nbsp")) {
						content = key;
					}
					Label label = new Label(cell.getColumn(), row, content);
					label.setCellFormat(getBodyCellStyle());
					wSheet.addCell(label);
				}
			} catch (Exception e) {
				logger.error("写入表格变量对象发生错误,错误信息:{}",e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取模板键名
	 * 
	 * @param pKey
	 *            模板元标记
	 * @return 键名
	 */
	private static String getKey(String pKey) {
		String key = null;
		try {
			int index = pKey.lastIndexOf(":");
			if (index == -1) {
				key = pKey.substring(3, pKey.length() - 1);
			} else {
				key = pKey.substring(3, index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

	/**
	 * 表头单元格样式的设定
	 */
	public WritableCellFormat getBodyCellStyle() {

		/*
		 * WritableFont.createFont("宋体")：设置字体为宋体 10：设置字体大小
		 * WritableFont.NO_BOLD:设置字体非加粗（BOLD：加粗 NO_BOLD：不加粗） false：设置非斜体
		 * UnderlineStyle.NO_UNDERLINE：没有下划线
		 */
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat bodyFormat = new WritableCellFormat(font);
		try {
			// 设置单元格背景色：表体为白色
			bodyFormat.setBackground(Colour.WHITE);
			// 设置表头表格边框样式
			// 整个表格线为细线、黑色
			bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			bodyFormat.setWrap(true);
		} catch (WriteException e) {
			System.out.println("表体单元格样式设置失败！");
		}
		return bodyFormat;
	}
	/**
	 * 表头单元格样式的设定
	 * 默认认为,如果是非内容部分,则为标题部分
	 */
	@SuppressWarnings("deprecation")
	public WritableCellFormat getTitleCellStyle() {

		/*
		 * WritableFont.createFont("宋体")：设置字体为宋体 10：设置字体大小
		 * WritableFont.NO_BOLD:设置字体非加粗（BOLD：加粗 NO_BOLD：不加粗） false：设置非斜体
		 * UnderlineStyle.NO_UNDERLINE：没有下划线
		 */
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 12,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat bodyFormat = new WritableCellFormat(font);
		try {
			// 设置单元格背景色：表体为白色
			bodyFormat.setBackground(Colour.WHITE);
			// 设置表头表格边框样式
			// 整个表格线为细线、黑色
			bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			bodyFormat.setWrap(true);
			////设置垂直对齐为居中对齐
			bodyFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			//水平对齐
			bodyFormat.setAlignment(jxl.write.Alignment.CENTRE);
		} catch (WriteException e) {
			System.out.println("表体单元格样式设置失败！");
		}
		return bodyFormat;
	}

	/**
	 * 获取模板单元格标记数据类型
	 * 
	 * @param pType
	 *            模板元标记
	 * @return 数据类型
	 */
	private static String getType(String pType) {
		String type = ExcelConstants.ExcelTPL_DataType_Label;
		if (pType.indexOf(":n") != -1 || pType.indexOf(":N") != -1) {
			type = ExcelConstants.ExcelTPL_DataType_Number;
		}
		if (pType.indexOf(":u") != -1 || pType.indexOf(":U") != -1) {
			type = ExcelConstants.ExcelTPL_DataType_Formual;
		}
		return type;
	}

	public ExcelTemplate getExcelTemplate() {
		return excelTemplate;
	}

	public void setExcelTemplate(ExcelTemplate excelTemplate) {
		this.excelTemplate = excelTemplate;
	}

	public ExcelData getExcelData() {
		return excelData;
	}

	public void setExcelData(ExcelData excelData) {
		this.excelData = excelData;
	}
}
