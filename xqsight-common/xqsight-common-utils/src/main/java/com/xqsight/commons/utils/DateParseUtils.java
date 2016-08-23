/**
 * 
 */
package com.xqsight.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xqsight-jerry
 * 
 */
public class DateParseUtils {

	private static final Map<String, ThreadLocal<SimpleDateFormat>> parseMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	private static final ThreadLocal<SimpleDateFormat> yyyyMMdd_threadlocal = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMdd");
		}
	};

	private static final ThreadLocal<SimpleDateFormat> yyMMdd_threadlocal = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyMMdd");
		}
	};

	private static final ThreadLocal<SimpleDateFormat> HHmmss_threadlocal = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat("HHmmss");
		}
	};

	private static final ThreadLocal<SimpleDateFormat> yyyy_MM_dd_threadlocal = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	private static final ThreadLocal<SimpleDateFormat> HH_mm_ss_threadlocal = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat("HH:mm:ss");
		}
	};

	private static final ThreadLocal<SimpleDateFormat> yyyyMMddHHmmss_threadlocal = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmss");
		}
	};

	private static final ThreadLocal<SimpleDateFormat> yyyy_MM_dd_SPACE_HH_mm_ss_threadlocal = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	
	private static final ThreadLocal<SimpleDateFormat> yyyy_SLASH_MM_SLASH_dd_SPACE_HH_mm_ss_threadlocal = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		}
	};

	private static final ThreadLocal<SimpleDateFormat> yyyyMMddHHmmssSSS_threadlocal = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmssSSS");
		}
	};

	static {
		parseMap.put("yyyyMMdd", yyyyMMdd_threadlocal);
		parseMap.put("yyMMdd", yyMMdd_threadlocal);
		parseMap.put("HHmmss", HHmmss_threadlocal);
		parseMap.put("yyyy-MM-dd", yyyy_MM_dd_threadlocal);
		parseMap.put("HH:mm:ss", HH_mm_ss_threadlocal);
		parseMap.put("yyyyMMddHHmmss", yyyyMMddHHmmss_threadlocal);
		parseMap.put("yyyy-MM-dd HH:mm:ss", yyyy_MM_dd_SPACE_HH_mm_ss_threadlocal);
		parseMap.put("yyyy/MM/dd HH:mm:ss", yyyy_SLASH_MM_SLASH_dd_SPACE_HH_mm_ss_threadlocal);
		parseMap.put("yyyyMMddHHmmssSSS", yyyyMMddHHmmssSSS_threadlocal);
	}

	public static Date yyyyMMdd(String dateText) throws ParseException {
		return parseMap.get("yyyyMMdd").get().parse(dateText);
	}

	public static Date yyMMdd(String dateText) throws ParseException {
		return parseMap.get("yyMMdd").get().parse(dateText);
	}

	public static Date HHmmss(String dateText) throws ParseException {
		return parseMap.get("HHmmss").get().parse(dateText);
	}

	public static Date yyyy_MM_dd(String dateText) throws ParseException {
		return parseMap.get("yyyy-MM-dd").get().parse(dateText);
	}

	public static Date HH_mm_ss(String dateText) throws ParseException {
		return parseMap.get("HH:mm:ss").get().parse(dateText);
	}

	public static Date yyyyMMddHHmmss(String dateText) throws ParseException {
		return parseMap.get("yyyyMMddHHmmss").get().parse(dateText);
	}

	public static Date yyyy_MM_dd_SPACE_HH_mm_ss(String dateText) throws ParseException {
		return parseMap.get("yyyy-MM-dd HH:mm:ss").get().parse(dateText);
	}
	
	public static Date yyyy_SLASH_MM_SLASH_dd_SPACE_HH_mm_ss(String dateText) throws ParseException {
		return parseMap.get("yyyy/MM/dd HH:mm:ss").get().parse(dateText);
	}

	public static Date yyyyMMddHHmmssSSS(String dateText) throws ParseException {
		return parseMap.get("yyyyMMddHHmmssSSS").get().parse(dateText);
	}
}
