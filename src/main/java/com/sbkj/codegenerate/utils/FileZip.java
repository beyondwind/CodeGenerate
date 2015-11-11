package com.sbkj.codegenerate.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileZip {

	public static void main(String[] args) throws Exception {
		File f = new File(
				"/Users/lijiabei/ProgramData/workspace_bugfix/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CodeGenerate/userTemp/E9975AC43E30A070A4D986842D05583Fproject");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream("/Users/lijiabei/test.zip"));
		zip(out, f, null);
		out.close();
	}

	public static void zip(ZipOutputStream out, File f, String base) throws Exception {
		System.out.println("zipping " + f.getAbsolutePath());
		if (f.isDirectory()) {
			File[] fc = f.listFiles();
			if (base != null) out.putNextEntry(new ZipEntry(base + "/"));
			base = base == null ? "" : base + "/";
			for (int i = 0; i < fc.length; i++) {
				zip(out, fc[i], base + fc[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			while ((b = in.read()) != -1)
				out.write(b);
			in.close();
		}
	}
}
