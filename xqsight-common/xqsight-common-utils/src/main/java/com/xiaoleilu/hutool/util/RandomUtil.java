package com.xiaoleilu.hutool.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 随机工具类
 * @author xiaoleilu
 *
 */
public class RandomUtil {
	private static Random random = new Random();
	
	private static Date date = new Date();
	private static StringBuilder buf = new StringBuilder();
	private static int seq = 1;
	private static final int ROTATION = 999999999;
	
	/** 用于随机选的数字 */
	private static final String BASE_NUMBER = "0123456789";
	/** 用于随机选的字符 */
	private static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
	/** 用于随机选的字符和数字 */
	private static final String BASE_CHAR_NUMBER = BASE_CHAR + BASE_NUMBER;
	
	/**
	 * 获得指定范围内的随机数
	 * @param min 最小数
	 * @param max 最大数
	 * @return 随机数
	 */
	public static int randomInt(int min, int max) {
		return random.nextInt(max - min) + min;
	}
	
	/**
	 * 获得随机数
	 * @return 随机数
	 */
	public static int randomInt() {
		return random.nextInt();
	}
	
	/**
	 * 获得指定范围内的随机数 [0,limit)
	 * @param limit 限制随机数的范围，不包括这个数
	 * @return 随机数
	 */
	public static int randomInt(int limit) {
		return random.nextInt(limit);
	}
	
	/**
	 * 随机获得列表中的元素
	 * @param <T> 元素类型
	 * @param list 列表
	 * @return 随机元素
	 */
	public static <T> T randomEle(List<T> list) {
		return randomEle(list, list.size());
	}
	
	/**
	 * 随机获得列表中的元素
	 * @param <T> 元素类型
	 * @param list 列表
	 * @param limit 限制列表的前N项
	 * @return  随机元素
	 */
	public static <T> T randomEle(List<T> list, int limit) {
		return list.get(randomInt(limit));
	}
	
	/**
	 * 随机获得列表中的一定量元素
	 * @param <T> 元素类型
	 * @param list 列表
	 * @param count 随机取出的个数
	 * @return 随机元素
	 */
	public static <T> List<T> randomEles(List<T> list, int count) {
		final List<T> result = new ArrayList<T>(count);
		int limit = list.size();
		while(--count > 0) {
			result.add(randomEle(list, limit));
		}
		
		return result;
	}
	
	/**
	 * 获得一个随机的字符串（只包含数字和字符）
	 * 
	 * @param length 字符串的长度
	 * @return 随机字符串
	 */
	public static String randomString(int length) {
		return randomString(BASE_CHAR_NUMBER, length);
	}
	
	/**
	 * 获得一个只包含数字的字符串
	 * 
	 * @param length 字符串的长度
	 * @return 随机字符串
	 */
	public static String randomNumbers(int length) {
		return randomString(BASE_NUMBER, length);
	}
	
	/**
	 * 获得一个随机的字符串
	 * 
	 * @param baseString 随机字符选取的样本
	 * @param length 字符串的长度
	 * @return 随机字符串
	 */
	public static String randomString(String baseString, int length) {
		StringBuffer sb = new StringBuffer();
		
		if (length < 1) {
			length = 1;
		}
		int baseLength = baseString.length();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(baseLength);
			sb.append(baseString.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * @return 随机UUID
	 */
	public static String randomUUID() {
		return UUID.randomUUID().toString();
	}
	

	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: orderNo
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	public static synchronized String getUnique() {
		if (seq > ROTATION)
			seq = 0;
		buf.delete(0, buf.length());
		date.setTime(System.currentTimeMillis());
		String str = String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$05d", date, seq++);
		return str;
	}

	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: getUnique
	 * @param @param prefix
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	public static synchronized String getUnique(String prefix) {
		if (seq > ROTATION)
			seq = 0;
		buf.delete(0, buf.length());
		date.setTime(System.currentTimeMillis());
		String str = String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$05d", date, seq++);
		return prefix + str;
	}
	
	
	public static void main(String[] args){
		System.out.println(getUnique());
		System.out.println(getUnique("king"));
	}
}
