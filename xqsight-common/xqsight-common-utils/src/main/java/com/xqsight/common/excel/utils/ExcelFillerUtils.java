package com.xqsight.common.excel.utils;

import com.xqsight.common.excel.ExcelConstants;
import com.xqsight.common.excel.ExcelFiller;
import com.xqsight.common.excel.ExcelSupport;
import com.xqsight.common.excel.ExcelTemplate;
import jxl.Cell;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelFillerUtils extends ExcelFiller {
	private static Logger logger = LogManager.getLogger(ExcelFillerUtils.class);

	private WritableWorkbook wwb = null;// 创建excel文件
	private WritableSheet wSheet = null;// 工作表
	private int sheetIndex = 0;
	private String sheetName = "";
	private Workbook wb;
	private ByteArrayOutputStream bos = new ByteArrayOutputStream();
	// 用户自定义样式：false表示使用模板原有样式；true表示使用自定义的样式
	private boolean customStyle = true;

	public ExcelFillerUtils() {}

	/**
	 * 构造函数
	 * 
	 * @param pExcelTemplate
	 */
	public ExcelFillerUtils(ExcelTemplate pExcelTemplate) {

		setExcelTemplate(pExcelTemplate);
		InputStream is = ExcelTemplate.class.getResourceAsStream(getExcelTemplate().getTemplatePath());

		try {
			wb = Workbook.getWorkbook(is);
			WorkbookSettings settings = new WorkbookSettings();
			settings.setWriteAccess(null);
			wwb = Workbook.createWorkbook(bos, wb, settings);
		} catch (BiffException e) {
			logger.error("基于模板生成可写工作表出错了,错误信息:{}",e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("基于模板生成可写工作表出错了,错误信息:{}",e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 数据填充 将ExcelData填入excel模板
	 */
	public void fill(int i) {
		try {
			wSheet = wwb.getSheet(i);
			if (!"".equals(this.sheetName) && this.sheetName != null) {
				wSheet.setName(this.sheetName);
			}
			// this.addSheet(getSheetName());
			fillStatics(wSheet, i);
			fillParameters(wSheet, i);
			fillFields(wSheet, i);
		} catch (Exception e) {
			logger.error("基于模板生成可写工作表出错了,错误信息:{}",e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除多余的sheet row column(反序删，序号从大到小）
	 * @param cleanTheDoc    sheetIndex:R|C|S:endRowIndex|endColumnIndex|endSheetIndex:deleteLength[,REPEAT]
	 */
	public void cleanTheDoc(String cleanTheDoc){
		if(cleanTheDoc == null){
			return;
		}
		String[] commands = cleanTheDoc.split(",");
		if(commands.length == 0){
			return;
		}
		for(int i = 0; i < commands.length; i++){
			String[] command = commands[i].split(":", 4);
			String type = command[1];
			switch (type.charAt(0)) {
			case 'R':
				WritableSheet sheetR = wwb.getSheet(Integer.valueOf(command[0]));
				int endR = Integer.valueOf(command[2]);
				int lengthR = Integer.valueOf(command[3]);
				int startR = endR - lengthR;
				for(int j = endR; j > startR; j--){
					sheetR.removeRow(j);
				}
				break;
			case 'C':
				WritableSheet sheetC = wwb.getSheet(Integer.valueOf(command[0]));
				int endC = Integer.valueOf(command[2]);
				int lengthC = Integer.valueOf(command[3]);
				int startC = endC - lengthC;
				for(int j = endC; j > startC; j--){
					sheetC.removeColumn(j);
				}
				break;
			case 'S':
				int endS = Integer.valueOf(command[2]);
				int lengthS = Integer.valueOf(command[3]);
				int startS = endS - lengthS;
				for(int j = endS; j > startS; j--){
					wwb.removeSheet(j);
				}
				break;
			default:
				break;
			}
			
		}
		
	}

	/**
	 * @return ByteArrayOutputStream
	 * 
	 */
	public ByteArrayOutputStream getByteArrayOutputStream() {
		return bos;
	}

	/**
	 * 关闭资源
	 */
	public void write() {
		try {
			wwb.write();
			wwb.close();
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 写入静态对象
	 */
	public void fillStatics(WritableSheet wSheet) {
		fillStatics(wSheet, null);
	}

	/**
	 * 写入静态对象
	 */
	public void fillStatics(WritableSheet wSheet, Integer sheetIdx) {
		List statics = null;
		if (getExcelTemplate().isMultiParamTemplate()) {
			statics = getExcelTemplate().getStaticObject(sheetIdx);
		} else {
			statics = getExcelTemplate().getStaticObject();
		}
		for (int i = 0; i < statics.size(); i++) {
			Cell cell = (Cell) statics.get(i);
			Label label = new Label(cell.getColumn(), cell.getRow(), cell.getContents());
			Cell thisCell = wSheet.getCell(cell.getColumn(), cell.getRow());
			if (this.isCustomStyle()) {
				label.setCellFormat(getTitleCellStyle());
			} else {
				label.setCellFormat(thisCell.getCellFormat());
			}

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
	public void fillParameters(WritableSheet wSheet) {
		fillParameters(wSheet, null);
	}

	/**
	 * 写入参数对象
	 */
	public void fillParameters(WritableSheet wSheet, Integer sheetIdx) {
		List parameters = null;
		if (getExcelTemplate().isMultiParamTemplate()) {
			parameters = getExcelTemplate().getParameterObjct(sheetIdx);
		} else {
			parameters = getExcelTemplate().getParameterObjct();
		}

		Map parameterMap = getExcelData().getParametersMap();
		for (int i = 0; i < parameters.size(); i++) {
			Cell cell = (Cell) parameters.get(i);
			String key = getKey(cell.getContents().trim());
			String type = getType(cell.getContents().trim());
			Cell thisCell = wSheet.getCell(cell.getColumn(), cell.getRow());
			try {
				if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Number)) {
					Number number = new Number(cell.getColumn(), cell.getRow(), MapUtils.getDoubleValue(parameterMap,key));
					// number.setCellFormat(null);//getBodyCellStyle()
					if (this.isCustomStyle()) {
						number.setCellFormat(getBodyCellStyle());
					} else {
						number.setCellFormat(thisCell.getCellFormat());
					}

					wSheet.addCell(number);
				} else {
					Label label = new Label(cell.getColumn(), cell.getRow(),MapUtils.getString(parameterMap,key));
					// label.setCellFormat(null);
					if (this.isCustomStyle()) {
						label.setCellFormat(getBodyCellStyle());
					} else {
						label.setCellFormat(thisCell.getCellFormat());
					}

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
	public void fillFields(WritableSheet wSheet) throws Exception {
		fillFields(wSheet, null);
	}

	/**
	 * 写入表格字段对象
	 * 
	 * @throws Exception
	 */
	public void fillFields(WritableSheet wSheet, Integer sheetIdx) throws Exception {
		List fields = null;
		if (getExcelTemplate().isMultiParamTemplate()) {
			fields = getExcelTemplate().getFieldObjct(sheetIdx);
		} else {
			fields = getExcelTemplate().getFieldObjct();
		}
		List fieldList = getExcelData().getFieldsList();
		for (int j = 0; j < fieldList.size(); j++) {
			Map dataMap = new HashMap<>();
			Object object = fieldList.get(j);
			if (object instanceof Map<?, ?>) {
				Map dto = (Map) object;
				dataMap.putAll(dto);
			} else {
				logger.error( "不支持的数据类型!");
			}
			for (int i = 0; i < fields.size(); i++) {
				Cell cell = (Cell) fields.get(i);
				String key = getKey(cell.getContents().trim());
				String type = getType(cell.getContents().trim());
				Cell thisCell = wSheet.getCell(cell.getColumn(), cell.getRow() + j);
				try {
					
					if("Percent".equals(MapUtils.getString(dataMap,"RenderType"))){
						Label label = new Label(cell.getColumn(), cell.getRow() + j, MapUtils.getString(dataMap,key));
						// label.setCellFormat(null);
						if (this.isCustomStyle()) {
							label.setCellFormat(getBodyCellStyle());
						} else {
							label.setCellFormat(thisCell.getCellFormat());
						}

						wSheet.addCell(label);
					}else if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Number)) {
						BigDecimal val = BigDecimal.valueOf(MapUtils.getDoubleValue(dataMap,key));
						if(val == null){
							Label label = new Label(cell.getColumn(), cell.getRow() + j, "");
							// label.setCellFormat(null);
							if (this.isCustomStyle()) {
								label.setCellFormat(getBodyCellStyle());
							} else {
								label.setCellFormat(thisCell.getCellFormat());
							}

							wSheet.addCell(label);
						}else{
							Number number = new Number(cell.getColumn(), cell.getRow() + j, val.doubleValue());
							// number.setCellFormat(null);
							if (this.isCustomStyle()) {
								number.setCellFormat(getBodyCellStyle());
							} else {
								number.setCellFormat(thisCell.getCellFormat());
							}
							wSheet.addCell(number);
						}

						
					} else if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Formual)) {
						Formula formual = new Formula(cell.getColumn(), cell.getRow() + j, key);
						wSheet.addCell(formual);
					} else {
						Label label = new Label(cell.getColumn(), cell.getRow() + j, MapUtils.getString(dataMap,key));
						// label.setCellFormat(null);
						if (this.isCustomStyle()) {
							label.setCellFormat(getBodyCellStyle());
						} else {
							label.setCellFormat(thisCell.getCellFormat());
						}

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
	public void fillVariables(WritableSheet wSheet, int row) {
		List variables = getExcelTemplate().getVariableObject();
		if(variables != null){
			Map parameterMap = getExcelData().getParametersMap();
			for (int i = 0; i < variables.size(); i++) {
				Cell cell = (Cell) variables.get(i);
				String key = getKey(cell.getContents().trim());
				String type = getType(cell.getContents().trim());
				Cell thisCell = wSheet.getCell(cell.getColumn(), row);
				try {
					if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Number)) {
						Number number = new Number(cell.getColumn(), row, MapUtils.getDoubleValue(parameterMap,key));
						// number.setCellFormat(null);
						number.setCellFormat(getTitleCellStyle());
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
						// formual.setCellFormat(null);
						if (this.isCustomStyle()) {
							formual.setCellFormat(getTitleCellStyle());
						} else {
							formual.setCellFormat(thisCell.getCellFormat());
						}
						wSheet.addCell(formual);
					} else {
						String content = MapUtils.getString(parameterMap,key);
						if (ExcelSupport.isEmpty(content) && !key.equalsIgnoreCase("nbsp")) {
							content = key;
						}
						Label label = new Label(cell.getColumn(), row, content);
						// label.setCellFormat(null);
						if (this.isCustomStyle()) {
							label.setCellFormat(getTitleCellStyle());
						} else {
							label.setCellFormat(thisCell.getCellFormat());
						}
						wSheet.addCell(label);
					}
				} catch (Exception e) {
					logger.error("写入表格变量对象发生错误,错误信息:{}",e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 表头单元格样式的设定
	 */
	public WritableCellFormat getBodyCellStyle() {
		/*
		 * WritableFont.createFont("宋体")：设置字体为宋体 10：设置字体大小 WritableFont.NO_BOLD:设置字体非加粗（BOLD：加粗 NO_BOLD：不加粗） false：设置非斜体 UnderlineStyle.NO_UNDERLINE：没有下划线
		 */
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat bodyFormat = new WritableCellFormat(font);
		try {
			// 设置单元格背景色：表体为白色
			// bodyFormat.setBackground(Colour.WHITE);
			// 设置表头表格边框样式
			// 整个表格线为细线、黑色
			bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			bodyFormat.setWrap(true);
		} catch (WriteException e) {
			System.out.println("表体单元格样式设置失败！");
		}
		return bodyFormat;
	}

	public WritableCellFormat getTitleCellStyle() {
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat bodyFormat = new WritableCellFormat(font);
		try {
			// 设置单元格背景色：表体为白色
			// bodyFormat.setBackground(Colour.WHITE);
			// 设置表头表格边框样式
			// 整个表格线为细线、黑色
			bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			bodyFormat.setWrap(true);
			// //设置垂直对齐为居中对齐
			bodyFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 水平对齐
			bodyFormat.setAlignment(jxl.format.Alignment.CENTRE);
		} catch (WriteException e) {
			System.out.println("表体单元格样式设置失败！");
		}
		return bodyFormat;
	}

	/**
	 * 获取模板键名
	 * 
	 * @param pKey
	 *            模板元标记
	 * @return 键名
	 */
	public static String getKey(String pKey) {
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
	 * 获取模板单元格标记数据类型
	 * 
	 * @param pType
	 *            模板元标记
	 * @return 数据类型
	 */
	public static String getType(String pType) {
		String type = ExcelConstants.ExcelTPL_DataType_Label;
		if (pType.indexOf(":n") != -1 || pType.indexOf(":N") != -1) {
			type = ExcelConstants.ExcelTPL_DataType_Number;
		}
		if (pType.indexOf(":u") != -1 || pType.indexOf(":U") != -1) {
			type = ExcelConstants.ExcelTPL_DataType_Formual;
		}
		return type;
	}

	public void addSheet(String sheetName) {
		// irow = -1;
		wSheet = wwb.createSheet(sheetName, sheetIndex++);
	}

	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * @param sheetName
	 *            the sheetName to set
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public boolean isCustomStyle() {
		return customStyle;
	}

	public void setCustomStyle(boolean customstyle) {
		this.customStyle = customstyle;
	}

}
