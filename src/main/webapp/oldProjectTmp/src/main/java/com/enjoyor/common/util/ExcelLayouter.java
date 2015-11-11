package com.enjoyor.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Title:Excel模板<br/>
 * Description:对导出的Excel进行自定义格式
 * 
 * @author ljb
 * @version 1.0 创建时间：2013-07-22
 */
public class ExcelLayouter {
	public static void buildReport(HSSFSheet worksheet, int startRowIndex,
			int startColIndex, String title, String[] headers, String condition) {
		// 设置列宽，统一5000
		for (int i = 0; i < headers.length; i++) {
			worksheet.setColumnWidth(i, 5000);
		}
		buildTitle(worksheet, startRowIndex, startColIndex, title,
				headers.length, condition);
		buildHeaders(worksheet, startRowIndex, startColIndex, headers);
	}

	private static void buildHeaders(HSSFSheet worksheet, int startRowIndex,
			int startColIndex, String[] headers) {
		// Header字体
		Font font = worksheet.getWorkbook().createFont();
		font.setBoldweight((short) Font.BOLDWEIGHT_BOLD);
		// font.setColor(HSSFColor.BLUE.index);//设置字体颜色

		// 单元格样式
		HSSFCellStyle headerCellStyle = worksheet.getWorkbook()
				.createCellStyle();

		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		headerCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		headerCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		headerCellStyle.setBorderTop(CellStyle.BORDER_THIN);

		// 创建字段标题
		HSSFRow rowHeader = worksheet.createRow((short) startRowIndex + 2);
		rowHeader.setHeight((short) 500);

		HSSFCell cell = null;
		for (int i = 0; i < headers.length; i++) {
			cell = rowHeader.createCell(startColIndex + i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(headerCellStyle);
		}
	}

	private static void buildTitle(HSSFSheet worksheet, int startRowIndex,
			int startColIndex, String title, int column, String condition) {
		// 报表标题字体
		Font fontTitle = worksheet.getWorkbook().createFont();
		fontTitle.setBoldweight((short) Font.BOLDWEIGHT_BOLD);
		fontTitle.setFontHeight((short) 280);

		// 标题单元格格式
		HSSFCellStyle cellStyleTitle = worksheet.getWorkbook()
				.createCellStyle();
		cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyleTitle.setWrapText(true);
		cellStyleTitle.setFont(fontTitle);

		HSSFRow rowTitle = worksheet.createRow((short) startRowIndex);
		rowTitle.setHeight((short) 500);
		HSSFCell cellTitle = rowTitle.createCell(startColIndex);
		cellTitle.setCellValue(title);
		cellTitle.setCellStyle(cellStyleTitle);

		worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, column - 1));// 标题合并列

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		HSSFRow dateTitle = worksheet.createRow((short) startRowIndex + 1);
		HSSFCell cellDate = dateTitle.createCell(startColIndex);
		condition = condition.equals("") ? "所有" : condition;
		cellDate.setCellValue("创建于: " + dateFormat.format(date) + " 查询条件："
				+ condition);
	}
}
