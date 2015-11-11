package com.enjoyor.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.util.HtmlUtils;

public final class StringUtil {

	protected static final Log log = LogFactory
			.getLog("com.xunware.project.util.StringUtil");

	public static int sequence;

	public static int seqMinValue = 1;

	public static int seqMaxValue = 65000;

	/**
	 * Hides the public constructor.
	 * <p>
	 */
	private StringUtil() {
		// noop
	}

	/**
	 * 根据源串数返回相同?串
	 * 
	 * @param s
	 *            源串
	 * @return ?, ?, ?
	 */
	public static String toColStr(Object[] s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; s != null && i < s.length; i++) {
			sb.append(", ?");
		}
		return sb.toString().replaceFirst(", ", "");
	}

	public static String ReplaceSQLChar(String str) {
		if (str == null || str.compareTo("") == 0) {
			return "";
		}

		str = str.replace("'", "‘");
		str = str.replace(";", "；");
		str = str.replace(",", ",");
		str = str.replace("?", "?");
		str = str.replace("<", "＜");
		str = str.replace(">", "＞");
		str = str.replace("(", "(");
		str = str.replace(")", ")");
		str = str.replace("@", "＠");
		str = str.replace("=", "＝");
		str = str.replace("+", "＋");
		str = str.replace("*", "＊");
		str = str.replace("&", "＆");
		str = str.replace("#", "＃");
		str = str.replace("%", "％");
		str = str.replace("$", "￥");

		return str;
	}

	/**
	 * 返回分页查询时的排序字符串
	 * 
	 * @param pk
	 *            主键列名(可使用多个主键)
	 * @return String 如：pk1, pk2 desc
	 */
	public static String toSQLPageConditionOrderBy(String[] pk) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; pk != null && i < pk.length; i++) {
			sb.append(", ");
			sb.append(pk[i]);
		}
		String s = sb.toString().replaceFirst(", ", "");
		return s;
	}

	/**
	 * 返回分页查询时的top后面的查询的主键列
	 * 
	 * @param pk
	 *            主键列名(可使用多个主键)
	 * @return String 如：pk1, pk2
	 */
	public static String toSQLPageConditionCol(String[] pk) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; pk != null && i < pk.length; i++) {
			sb.append(", ");
			sb.append(pk[i]);
		}
		return sb.toString().replaceFirst(", ", "");
	}

	/**
	 * 返回分页查询时的On后面的条件
	 * 
	 * @param pk
	 *            主键列名(可使用多个主键)
	 * @return String 如：a.pk1=b.pk1 and a.pk2=b.pk2
	 */
	public static String toSQLPageConditionOn(String[] pk) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; pk != null && i < pk.length; i++) {
			sb.append(" and a.");
			sb.append(pk[i]);
			sb.append("=b.");
			sb.append(pk[i]);
		}
		return sb.toString().replaceFirst(" and ", "");
	}

	/**
	 * 返回分页查询时的Where后面的条件
	 * 
	 * @param pk
	 *            主键列名(可使用多个主键)
	 * @return String 如：b.pk1 is null and b.pk2 is null
	 */
	public static String toSQLPageConditionWhere(String[] pk) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; pk != null && i < pk.length; i++) {
			sb.append(" and b.");
			sb.append(pk[i]);
			sb.append(" is null");
		}
		return sb.toString().replaceFirst(" and ", "");
	}

	/**
	 * 
	 * @param inTimeStr
	 *            输入时间字符串
	 * @return 过滤了分隔字符的时间串
	 */
	public static String toCompartStr(String inTimeStr) {
		return inTimeStr.replaceAll("[-: ]", "");
	}

	/**
	 * 字符串数组强制转换成整形数组
	 * 
	 * @param srcStr
	 *            输入字符串数组
	 * @return 整形数组
	 */
	public static int[] getStrings2Ints(String[] srcStr) {
		if (srcStr != null) {
			int[] in = new int[srcStr.length];
			for (int i = 0; i < srcStr.length; i++) {
				if (srcStr[i].length() > 0) {
					try {
						in[i] = Integer.parseInt(srcStr[i]);
					} catch (Exception e) {
						log.error(e);
						e.printStackTrace();
					}
				}
			}
			return in;
		} else {
			return null;
		}
	}

	/**
	 * 按指定长度返回数字字符串，缺位补零
	 * 
	 * @param srcInt
	 * @param bitSize
	 * @return 0001231
	 */
	public static String getInt2StringOnSizeFillZero(int srcInt, int bitSize) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(srcInt));
		if (sb.length() > bitSize) {
			return sb.substring(bitSize - sb.length());
		} else if (sb.length() == bitSize) {
			return sb.toString();
		} else {
			int size = bitSize - sb.length();
			StringBuffer sb0 = new StringBuffer();
			for (int i = 0; i < size; i++) {
				sb0.append("0");
			}
			return sb0.append(sb.toString()).toString();
		}
	}

	/**
	 * 转换日期格式
	 * 
	 * @param dt
	 *            yyyy-mm-dd hh24:mi:ss dw(19位)
	 * @param format
	 *            任意的yyyy、mm、dd、hh24、mi、ss、dw(星期几)组合
	 * @return
	 */
	public static String getDateWeek2Format(String dtw, String format)
			throws Exception {
		if (dtw.length() > 20) {
			format = format.toLowerCase();
			format = getDate2Format(dtw.substring(0, 19), format);
			format = format.replaceAll("dw", dtw.substring(20));
			return format;
		} else {
			Exception ce = new Exception("源日期格式不正确");
			throw ce;
		}
	}

	/**
	 * 转换日期格式
	 * 
	 * @param dt
	 *            yyyy-mm-dd hh24:mi:ss(19位)
	 * @param format
	 *            任意的yyyy、mm、dd、hh24、mi、ss组合
	 * @return
	 */
	public static String getDate2Format(String dt, String format)
			throws Exception {
		if (dt.length() == 19) {
			format = format.toLowerCase();
			if (format.indexOf("yyyy") >= 0) {
				format = format.replaceAll("yyyy", dt.substring(0, 4));
			}
			if (format.indexOf("mm") >= 0) {
				format = format.replaceAll("mm", dt.substring(5, 7));
			}
			if (format.indexOf("dd") >= 0) {
				format = format.replaceAll("dd", dt.substring(8, 10));
			}
			if (format.indexOf("hh24") >= 0) {
				format = format.replaceAll("hh24", dt.substring(11, 13));
			}
			if (format.indexOf("hh") >= 0) {
				format = format.replaceAll("hh", dt.substring(11, 13));
			}
			if (format.indexOf("mi") >= 0) {
				format = format.replaceAll("mi", dt.substring(14, 16));
			}
			if (format.indexOf("ss") >= 0) {
				format = format.replaceAll("ss", dt.substring(17));
			}
			return format;
		} else {
			Exception ce = new Exception("源日期格式不正确");
			throw ce;
		}
	}

	/**
	 * 获取本地时间转换日期格式 日期转换成200909形式
	 * 
	 * @return
	 */
	public static String getDate() {
		String time = new String("");
		String year = new String("");
		String month = new String("");
		Date date = new Date();

		time = DateFormat.getDateInstance(DateFormat.DEFAULT).format(date);

		year = time.substring(0, time.indexOf("-", 0));
		month = time.substring(time.indexOf("-", 0) + 1, time.lastIndexOf("-"));
		if (month.length() == 1) {
			month = "0" + month;
		}
		time = year + month;
		return time;
	}

	/**
	 * 获取本地年份+所选择的月份 拼接成形如200909的日期格式
	 * 
	 * @author
	 * @return
	 */
	public static String getSelectDate(String time) {
		String year = new String("");
		String month = new String("");

		year = time.substring(0, time.indexOf("-", 0));
		month = time.substring(time.indexOf("-", 0) + 1, time.lastIndexOf("-"));
		if (month.length() == 1) {
			month = "0" + month;
		}
		time = year + month;
		return time;
	}

	/**
	 * 获取所选择的月份
	 * 
	 * @author
	 * @return
	 */
	public static int getSelectMonth(String time) {

		int month = 0;
		month = Integer.parseInt(time.substring(time.indexOf("-", 0) + 1,
				time.lastIndexOf("-")));
		return month;
	}

	/**
	 * 获取所选择的年份
	 * 
	 * @author
	 * @return
	 */
	public static int getSelectYear(String time) {

		int yaer = 0;
		yaer = Integer.parseInt(time.substring(0, time.indexOf("-", 0)));
		return yaer;
	}

	/**
	 * 去掉内容中的html标签
	 * 
	 * @author
	 * @return String
	 */
	public static String htmlToStr(String htmlStr) {
		String result = "";
		// boolean flag = true;
		if (htmlStr == null) {
			return null;
		}
		// char[] a = htmlStr.toCharArray();
		// int length = a.length;
		// for (int i = 0; i < length; i++) {
		// if (a[i] == '<') {
		// flag = false;
		// continue;
		// }
		// if (a[i] == '>') {
		// flag = true;
		// continue;
		// }
		// if (flag == true) {
		// result += a[i];
		// }
		// }
		result = htmlStr.replaceAll("<[^>]*>", "");
		if (result.length() > 210) {
			result = result.substring(0, 160) + "...";
		}
		return result.toString();
	}

	/**
	 * 去掉内容中的html标签
	 * 
	 * @author
	 * @return String
	 */
	public static String htmlToStr2(String htmlStr, boolean marker) {
		String result = "";
		// boolean flag = true;
		if (htmlStr == null) {
			return null;
		}
		// char[] a = htmlStr.toCharArray();
		// int length = a.length;
		// for (int i = 0; i < length; i++) {
		// if (a[i] == '<') {
		// flag = false;
		// continue;
		// }
		// if (a[i] == '>') {
		// flag = true;
		// continue;
		// }
		// if (flag == true) {
		// result += a[i];
		// }
		// }
		result = htmlStr.replaceAll("<[^>]*>", "");
		if (marker == true) {
			if (result.length() > 210) {
				result = result.substring(0, 160) + "...";
			}
		}
		return result.toString();
	}

	/**
	 * 转换字符串为html转义字符
	 */
	public static String dohtm(String str) {
		str = HtmlUtils.htmlEscape(str);
		str = str.replaceAll("\r?\n", "<br/>");
		return str;
	}

	/**
	 * 根据byte截取字符串长度
	 * 
	 * @author
	 * @return String
	 */
	public static String bSubstring(String s, int length)
			throws UnsupportedEncodingException {

		byte[] bytes = s.getBytes("Unicode");
		int n = 0;
		int i = 2;
		for (; i < bytes.length && n < length; i++) {
			// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
			if (i % 2 == 1) {
				n++; // 在UCS2第二个字节时n加1
			} else {
				// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
				if (bytes[i] != 0) {
					n++;
				}
			}
		}
		// 如果i为奇数时，处理成偶数
		if (i % 2 == 1)

		{
			// 该UCS2字符是汉字时，去掉这个截一半的汉字
			if (bytes[i - 1] != 0)
				i = i - 1;
			// 该UCS2字符是字母或数字，则保留该字符
			else
				i = i + 1;
		}

		return new String(bytes, 0, i, "Unicode");
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @author 朱岳胜
	 * @param s
	 *            需要得到长度的字符串
	 * @return i得到的字符串长度
	 */

	public static String cSubstring(String origin, int len, String c) {
		if (origin == null || origin.equals("") || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len >= length(origin)) {
			return origin;
		}
		try {
			len -= 2;
			System.arraycopy(origin.getBytes("GBK"), 0, strByte, 0, len);
			int count = 0;
			for (int i = 0; i < len; i++) {
				int value = (int) strByte[i];
				if (value < 0) {
					count++;
				}
			}
			if (count % 2 != 0) {
				len = (len == 1) ? ++len : --len;
			}
			return new String(strByte, 0, len, "GBK") + c;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
	 * 
	 * @author 朱岳胜
	 * @param c
	 *            需要判断的字符
	 * @return 返回true,Ascill字符
	 */
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param s
	 *            需要得到长度的字符串
	 * @return i得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	/**
	 * 根据一个double的数字变成百分数
	 * 
	 * @param s
	 *            需要得到长度的字符串
	 * @return i得到的字符串长度
	 */
	public static String getPercentage(double number) {
		NumberFormat num = NumberFormat.getPercentInstance();
		num.setMaximumIntegerDigits(3);
		num.setMaximumFractionDigits(1);
		return num.format(number);
	}

	public static void main(String[] args) {
		log.info(StringUtil.dohtm("<P>\"HTML\" 特殊字符转义\r\n</P>"));
		log.info(StringUtil.getInt2StringOnSizeFillZero(123, 5));
		log.info(StringUtil.getInt2StringOnSizeFillZero(1, 5));
		log.info(StringUtil.getInt2StringOnSizeFillZero(123, 6));
		log.info(StringUtil.getInt2StringOnSizeFillZero(12, 50));
		log.info(StringUtil.getInt2StringOnSizeFillZero(123, 5));
		System.out.println(getUniqueString());
		System.out.println(getUniqueString());
		System.out.println(getUniqueString());
		System.out.println(getUniqueString());
		System.out.println(getUniqueString());
		System.out.println(getUniqueString());
		System.out.println(getUniqueString());
		System.out.println(getUniqueString());
		System.out.println(getUniqueString());
		System.out.println(getUniqueString());
		System.out.println(getUniqueString());
		System.out.println(getUniqueString());

	}

	/**
	 * 替换符号
	 */
	static String fuhao = "[；;。，,！!’“？：:、/r/n 	]";

	public static String replacement(String queryByName, String fenge) {
		return queryByName.replaceAll(fuhao, fenge);
	}

	// 用正则表达式 ,判断是否为数字
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否是字母串
	 * 
	 * @param word
	 * @return 是否全字母
	 */
	public static boolean isLetterStr(String word) {
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
				return false;
			}
		}
		return true;
	}

	public static String replaceTag(String input) {
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i <= input.length() - 1; i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			default:
				filtered.append(c);
			}
		}
		return (filtered.toString());
	}

	public static String getID() {
		Random random1 = new Random(10000);
		String str = String.valueOf(System.currentTimeMillis())
				+ String.valueOf(random1.nextInt());
		return str;
	}

	public static boolean notEmpty(String str) {
		if (str == null || str.compareTo("") == 0) {
			return false;
		}
		return true;
	}

	public synchronized static int getSequence() {
		if (sequence < seqMinValue) {
			sequence = seqMinValue;
		} else if (sequence >= seqMaxValue) {
			sequence = seqMinValue;
		} else {
			sequence++;
		}
		return sequence;
	}

	public static String getProperties(String key) {
		InputStream inputStream = StringUtil.class.getClassLoader()
				.getResourceAsStream("resources.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p.getProperty(key);
	}

	public static String getUniqueString() {

		java.text.SimpleDateFormat dfmoth = new java.text.SimpleDateFormat(
				"yyyyMMddHHmmss");
		// String ID
		// =dfmoth.format(Calendar.getInstance().getTime())+Double.toString(Math.random()).substring(2,
		// 10);
		String ID = dfmoth.format(Calendar.getInstance().getTime())
				+ genRandomNum(8);
		return ID;
	}

	/**
	 * 生成指定长度的随机数
	 * 
	 * @param pwd_len
	 *            生成随机数的总长度
	 * @return 随机数字符串
	 */
	public static String genRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 10;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		/*
		 * char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
		 * 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
		 * 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		 */

		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

	public static String getPropertiesFromResources(String key) {
		InputStream in = null;
		String value = null;
		try {
			in = new ClassPathResource("resources.properties").getInputStream();
			Properties p = new Properties();
			p.load(in);
			value = p.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	public static Map<String, String> getAllPropertiesFromResources() {
		return getAllPropertiesFromResources(null);
	}

	public static Map<String, String> getAllPropertiesFromResources(
			String filePath) {
		InputStream in = null;
		Map<String, String> m = null;
		try {
			in = new ClassPathResource(
					filePath == null ? "resources.properties" : filePath)
					.getInputStream();
			Properties p = new Properties();
			p.load(in);
			Enumeration en = p.propertyNames();
			m = new HashMap<String, String>();
			String key = null;
			while (en.hasMoreElements()) {
				key = (String) en.nextElement();
				m.put(key, p.getProperty(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return m;
	}

}
