/**
 * 
 */
package com.xqsight.common.utils;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * @author xqsight-jerry
 * 
 */
public class DateFormatUtils {

	public static final FastDateFormat yyyyMMdd = FastDateFormat.getInstance("yyyyMMdd");
	public static final FastDateFormat yyMMdd = FastDateFormat.getInstance("yyMMdd");
	public static final FastDateFormat MMdd = FastDateFormat.getInstance("MMdd");
	public static final FastDateFormat HHmmss = FastDateFormat.getInstance("HHmmss");
	public static final FastDateFormat HHmmssSSS = FastDateFormat.getInstance("HHmmssSSS");
	
	public static final FastDateFormat yyyy_MM_dd = FastDateFormat.getInstance("yyyy-MM-dd");
	public static final FastDateFormat HH_mm_ss = FastDateFormat.getInstance("HH:mm:ss");
	
	public static final FastDateFormat yyyy_SLASH_MM_SLASH_dd = FastDateFormat.getInstance("yyyy/MM/dd");
	
	public static final FastDateFormat yyyyMMddHHmmss = FastDateFormat.getInstance("yyyyMMddHHmmss");
	public static final FastDateFormat yyyy_MM_dd_SPACE_HH_mm_ss = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
	public static final FastDateFormat yyyyMMddHHmmssSSS = FastDateFormat.getInstance("yyyyMMddHHmmssSSS");
	public static final FastDateFormat yyyyMMdd_SPACE_HH_mm_ss = FastDateFormat.getInstance("yyyyMMdd HH:mm:ss");

}
