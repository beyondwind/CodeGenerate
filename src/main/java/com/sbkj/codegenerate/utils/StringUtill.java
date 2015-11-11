package com.sbkj.codegenerate.utils;

public class StringUtill {
	/**
	 * 替换字符串
	 * 
	 * @param line
	 *            --- 原字符串
	 * @param oldString
	 *            需要替换的字符串
	 * @param newString
	 *            替换进去的字符串
	 * @return string
	 */
	public static final String replace(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		i = line.indexOf(oldString, i);
		if (i >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * 判断字符串参数是否为空
	 * 
	 * @param s
	 * @return boolean
	 */
	public static final boolean isEmptyString(String s) {
		return s == null || s.trim().equals("") || s.equals("null")
				|| s.equals("NULL") || s.trim().equals("undefined");
	}

	/**
	 * 修饰获取到的数据库表名称，表名称将XXXX_XXXXX转化为形式为XxxXxxxx
	 * 
	 * @param oldName
	 * @return String
	 */
	public static final String modifyTableName(String oldName) {
		if (oldName == null)
			return "";
		String[] name = oldName.toLowerCase().split("_");
		String newName = "";
		for (int i = 0; i < name.length; i++) {
			newName = newName + name[i].substring(0, 1).toUpperCase()
					+ name[i].substring(1).toLowerCase();
		}
		return newName;
	}

	/**
	 * 修饰获取到的列名称，将XXXX_XXXXX转化为形式为xxxXxxxx
	 * 
	 * @param oldName
	 * @return String
	 */
	public static final String modifyColumnName(String oldName) {
		if (oldName == null)
			return "";
		String[] name = oldName.toLowerCase().split("_");
		String newName = "";
		newName = name[0].toLowerCase();
		for (int i = 1; i < name.length; i++) {
			newName = newName + name[i].substring(0, 1).toUpperCase()
					+ name[i].substring(1).toLowerCase();
		}
		return newName;
	}

	public static void main(String[] args) {
		String s = "dasds_dasd_dd";
		System.out.println(modifyTableName(s));
	}
}
