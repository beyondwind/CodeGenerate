package com.sbkj.codegenerate.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

	/**
	 * 复制单个文件
	 * 
	 * @param srcFileName 待复制的文件名
	 * @param descFileName 目标文件名
	 * @param overlay 如果目标文件存在，是否覆盖
	 * @return 如果复制成功返回true，否则返回false
	 */
	public static boolean copyFile(String srcFileName, String destFileName, boolean overlay) {
		File srcFile = new File(srcFileName);

		// 判断源文件是否存在，或者目标不是一个文件
		if (!srcFile.exists() || !srcFile.isFile()) {
			return false;
		}

		// 判断目标文件是否存在
		File destFile = new File(destFileName);
		if (destFile.exists()) {
			// 如果目标文件存在并允许覆盖
			if (overlay) {
				// 删除已经存在的目标文件，无论目标文件是目录还是单个文件
				new File(destFileName).delete();
			}
		} else {
			// 如果目标文件所在目录不存在，则创建目录
			if (!destFile.getParentFile().exists()) {
				// 目标文件所在目录不存在
				if (!destFile.getParentFile().mkdirs()) {
					// 复制文件失败：创建目标文件所在目录失败
					return false;
				}
			}
		}

		// 复制文件
		int byteread = 0; // 读取的字节数
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];

			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (out != null) out.close();
				if (in != null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 复制整个目录的内容
	 * 
	 * @param srcDirName 待复制目录的目录名
	 * @param destDirName 目标目录名
	 * @param overlay 如果目标目录存在，是否覆盖
	 * @return 如果复制成功返回true，否则返回false
	 */
	public static boolean copyDirectory(String srcDirName, String destDirName, boolean overlay) {
		// 判断源目录是否存在
		File srcDir = new File(srcDirName);
		if (!srcDir.exists() || !srcDir.isDirectory()) {
			return false;
		}

		// 如果目标目录名不是以文件分隔符结尾，则加上文件分隔符
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		File destDir = new File(destDirName);
		// 如果目标文件夹存在
		if (destDir.exists()) {
			// 如果允许覆盖则删除已存在的目标目录
			if (overlay) {
				new File(destDirName).delete();
			} else {
				return false;
			}
		} else {
			// 创建目的目录
			if (!destDir.mkdirs()) {
				return false;
			}
		}

		boolean flag = true;
		File[] files = srcDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 复制文件
			if (files[i].isFile()) {
				flag = FileUtil.copyFile(files[i].getAbsolutePath(), destDirName + files[i].getName(), overlay);
				if (!flag) break;
			} else if (files[i].isDirectory()) {
				flag = FileUtil.copyDirectory(files[i].getAbsolutePath(), destDirName + files[i].getName(), overlay);
				if (!flag) break;
			}
		}
		if (!flag) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 在指定路径下创建包的文件夹
	 * 
	 * @param packge 包
	 * @param path 目标路径
	 * @return boolean 若成功创建返回true
	 */
	public static boolean createDirByPackge(String packge, String path) {
		// 如果目标目录名不是以文件分隔符结尾，则加上文件分隔符
		if (!path.endsWith(File.separator)) {
			path = path + File.separator;
		}
		File destDir = new File(path);
		// 如果目标文件夹不存在
		if (!destDir.exists()) {
			if (!destDir.mkdirs()) {
				return false;// 目标文件夹创建失败
			}
		}
		String[] packgePaths = packge.split("\\.");
		String packgePath = path;
		File tempDir = null;
		for (int i = 0; i < packgePaths.length; i++) {
			packgePath = packgePath + packgePaths[0] + File.separator;
			tempDir = new File(packgePath);
			if (!tempDir.exists()) if (!tempDir.mkdir()) return false;
		}
		return true;
	}

	/**
	 * 在指定路径下创建包的文件夹
	 * 
	 * @param packgePaths 包层级数组
	 * @param path 目标路径
	 * @return String 若成功创建返回创建的路径
	 */
	public static String createDirByPackge(String[] packgePaths, String path) {
		// 如果目标目录名不是以文件分隔符结尾，则加上文件分隔符
		if (!path.endsWith(File.separator)) {
			path = path + File.separator;
		}
		File destDir = new File(path);
		// 如果目标文件夹不存在
		if (!destDir.exists()) {
			if (!destDir.mkdirs()) {
				return path;// 目标文件夹创建失败
			}
		}
		String packgePath = path;
		File tempDir = null;
		for (int i = 0; i < packgePaths.length; i++) {
			packgePath = packgePath + packgePaths[i] + File.separator;
			tempDir = new File(packgePath);
			if (!tempDir.exists()) if (!tempDir.mkdir()) return path;
		}
		return packgePath;
	}

	// 删除文件夹
	// param folderPath 文件夹完整绝对路径
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + File.separator + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
}
