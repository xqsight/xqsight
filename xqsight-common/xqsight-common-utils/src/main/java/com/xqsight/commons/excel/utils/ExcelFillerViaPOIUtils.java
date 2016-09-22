package com.xqsight.commons.excel.utils;

import com.xqsight.commons.excel.ExcelConstants;
import com.xqsight.commons.excel.ExcelFiller;
import com.xqsight.commons.excel.ExcelSupport;
import com.xqsight.commons.excel.ExcelTemplate;
import jxl.Cell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelFillerViaPOIUtils extends ExcelFiller {
	private static Logger logger = LogManager.getLogger(ExcelFillerViaPOIUtils.class);

	private WritableSheet wSheet = null;// 工作表
	private HSSFSheet hSheet = null;
	private WritableWorkbook wwb = null;// 创建excel文件
	private int sheetIndex = 0;
	private String sheetName = "";
	private HSSFWorkbook hwb = null;
	private ByteArrayOutputStream bos = new ByteArrayOutputStream();
	private boolean customStyle = true;// 用户自定义样式：false表示使用模板原有样式；true表示使用自定义的样式
	
	private HSSFCellStyle bodyHCs = null;
	private HSSFCellStyle titleHCs = null;
	private HSSFFont bodyHFont = null;
	private HSSFFont titleHFont = null;

	public ExcelFillerViaPOIUtils() {}

	public HSSFWorkbook getHwb() {
		return hwb;
	}

	/**
	 * 构造函数
	 * 
	 * @param pExcelTemplate
	 */
	public ExcelFillerViaPOIUtils(ExcelTemplate pExcelTemplate) {

		setExcelTemplate(pExcelTemplate);
//		InputStream is = ExcelTemplate.class.getResourceAsStream(getExcelTemplate().getTemplatePath());
		BufferedInputStream bis = new BufferedInputStream(ExcelTemplate.class.getResourceAsStream(getExcelTemplate().getTemplatePath()));
		POIFSFileSystem fs = null;
		try {
			fs = new POIFSFileSystem(bis);
			hwb = new HSSFWorkbook(fs);
//			wb = Workbook.getWorkbook(is);
		}  catch (IOException e) {
			logger.error("基于模板生成可写工作表出错了!");
			e.printStackTrace();
		}
	}

	/**
	 * 数据填充 将ExcelData填入excel模板
	 * 
	 * 
	 */
	public void fill(int i) {
		try {
			hSheet = hwb.getSheetAt(i);
			fillStatics(i);
			fillParameters(i);
			fillFields(i);
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
//			wb.close();
			hwb.write(bos);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 写入静态对象
	 */
	public void fillStatics() {
		fillStatics(null);
	}

	/**
	 * 写入静态对象
	 */
	public void fillStatics(Integer sheetIdx) {
		List statics = null;
		if (getExcelTemplate().isMultiParamTemplate()) {
			statics = getExcelTemplate().getStaticObject(sheetIdx);
		} else {
			statics = getExcelTemplate().getStaticObject();
		}
		for (int i = 0; i < statics.size(); i++) {
			Cell cell = (Cell) statics.get(i);
			HSSFRow hRow = hSheet.getRow(cell.getRow());
			if(hRow == null){
				hRow = hSheet.createRow(cell.getRow());
			}
			HSSFCell hCell = hRow.getCell(cell.getColumn());
			if(hCell == null){
				hCell = hRow.createCell(cell.getColumn());
			}
			hCell.setCellValue(cell.getContents());
			if (this.isCustomStyle()) {
				hCell.setCellStyle(getTitleHSSFCellStyle());
			}
		}
	}
	
	/**
	 * 写入参数对象
	 */
	public void fillParameters(){
		fillParameters(null);
	}

	/**
	 * 写入参数对象
	 */
	public void fillParameters(Integer sheetIdx) {
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
			try {
				if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Number)) {
					HSSFRow hRow = hSheet.getRow(cell.getRow());
					if(hRow == null){
						hRow = hSheet.createRow(cell.getRow());
					}
					HSSFCell hCell = hRow.getCell(cell.getColumn());
					if(hCell == null){
						hCell = hRow.createCell(cell.getColumn());
					}
					hCell.setCellValue(MapUtils.getDoubleValue(parameterMap,key));
					// number.setCellFormat(null);//getBodyCellStyle()
					if (this.isCustomStyle()) {
						hCell.setCellStyle(getBodyHSSFCellStyle());
					}

				} else {
					HSSFRow hRow = hSheet.getRow(cell.getRow());
					if(hRow == null){
						hRow = hSheet.createRow(cell.getRow());
					}
					HSSFCell hCell = hRow.getCell(cell.getColumn());
					if(hCell == null){
						hCell = hRow.createCell(cell.getColumn());
					}
					hCell.setCellValue(MapUtils.getString(parameterMap,key));
					// label.setCellFormat(null);
					if (this.isCustomStyle()) {
						hCell.setCellStyle(getBodyHSSFCellStyle());
					} 

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
	public void fillFields() throws Exception {
		fillFields(null);
	}

	/**
	 * 写入表格字段对象
	 * 
	 * @throws Exception
	 */
	public void fillFields(Integer sheetIdx) throws Exception {
		List fields = null;
		if (getExcelTemplate().isMultiParamTemplate()) {
			fields = getExcelTemplate().getFieldObjct(sheetIdx);
		} else {
			fields = getExcelTemplate().getFieldObjct();
		}
		List fieldList = getExcelData().getFieldsList();
		for (int j = 0; j < fieldList.size(); j++) {
			Map dataMap = new HashMap<>();
			HSSFRow hRow = null;
			Object object = fieldList.get(j);
			if (object instanceof Map<?, ?>) {
				Map domain = (Map) object;
				dataMap.putAll(domain);
			} else {
				logger.error("不支持的数据类型!");
			}
			for (int i = 0; i < fields.size(); i++) {
				Cell cell = (Cell) fields.get(i);
				String key = getKey(cell.getContents().trim());
				String type = getType(cell.getContents().trim());
				if(hRow == null){
					hRow = hSheet.createRow(cell.getRow() + j);
				}
				try {
					if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Number)) {
						BigDecimal val = BigDecimal.valueOf(MapUtils.getDoubleValue(dataMap,key));
						if(val == null){
							HSSFCell hCell = hRow.createCell(cell.getColumn());
							hCell.setCellValue("");
							if (this.isCustomStyle()) {
								hCell.setCellStyle(getBodyHSSFCellStyle());
							}
						}else{
							HSSFCell hCell = hRow.createCell(cell.getColumn());
							hCell.setCellValue(val.doubleValue());
							if (this.isCustomStyle()) {
								hCell.setCellStyle(getBodyHSSFCellStyle());
							}
						}

						
					} else if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Formual)) {
						HSSFCell hCell = hRow.createCell(cell.getColumn());
						hCell.setCellFormula(key);
						if (this.isCustomStyle()) {
							hCell.setCellStyle(getBodyHSSFCellStyle());
						}
					} else {
						HSSFCell hCell = hRow.createCell(cell.getColumn());
						hCell.setCellValue(MapUtils.getString(dataMap,key));
						if (this.isCustomStyle()) {
							hCell.setCellStyle(getBodyHSSFCellStyle());
						}
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
				try {
					if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Number)) {
						HSSFCell hCell = hSheet.getRow(cell.getRow()).getCell(cell.getColumn());
						hCell.setCellValue(MapUtils.getDoubleValue(parameterMap,key));
						if (this.isCustomStyle()) {
							hCell.setCellStyle(getTitleHSSFCellStyle());
						}
					} else if (type.equalsIgnoreCase(ExcelConstants.ExcelTPL_DataType_Formual)) {
						/*
						 * 转义,$R表示当前行数
						 */
						if (key.indexOf("$R") > -1) {
							key = key.replaceAll("\\$R", String.valueOf(row));
						}

						HSSFCell hCell = hSheet.getRow(cell.getRow()).getCell(cell.getColumn());
						hCell.setCellFormula(key);
						if (this.isCustomStyle()) {
							hCell.setCellStyle(getTitleHSSFCellStyle());
						}
					} else {
						String content = MapUtils.getString(parameterMap,key);
						if (ExcelSupport.isEmpty(content) && !key.equalsIgnoreCase("nbsp")) {
							content = key;
						}
						HSSFCell hCell = hSheet.getRow(cell.getRow()).getCell(cell.getColumn());
						hCell.setCellValue(content);
						if (this.isCustomStyle()) {
							hCell.setCellStyle(getTitleHSSFCellStyle());
						}
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
	public HSSFCellStyle getBodyHSSFCellStyle() {
		
		if(this.bodyHCs == null){
			this.bodyHCs = hwb.createCellStyle();
//			this.bodyHCs.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
//			this.bodyHCs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			this.bodyHCs.setFont(getBodyHFont());
			this.bodyHCs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			this.bodyHCs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			this.bodyHCs.setBorderRight(HSSFCellStyle.BORDER_THIN);
			this.bodyHCs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		}
		
		return this.bodyHCs;
	}

	public HSSFCellStyle getTitleHSSFCellStyle() {

		if(this.titleHCs == null){
			this.titleHCs = hwb.createCellStyle();
//			titleHCs.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
//			titleHCs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			this.titleHCs.setFont(getBodyHFont());
			this.titleHCs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			this.titleHCs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			this.titleHCs.setBorderRight(HSSFCellStyle.BORDER_THIN);
			this.titleHCs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		}
		
		return this.titleHCs;
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

	
	public HSSFFont getTitleHFont(){
		if(this.titleHFont == null){
			this.titleHFont = hwb.createFont();
			this.titleHFont.setFontName("宋体");
			this.titleHFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
			return this.titleHFont;
	}
	
	public HSSFFont getBodyHFont(){
		if(this.bodyHFont == null){
			this.bodyHFont = hwb.createFont();
			this.bodyHFont.setFontName("宋体");
		}
		return this.bodyHFont;
	}
}
