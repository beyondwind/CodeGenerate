package com.sbkj.codegenerate.domain;

import java.util.List;

public class CodeTable {

	private String tableName;// 表名
	private String tableComments;// 表备注
	private String tableNamePolished;// 表名称，去除下划线的

	private CodeColumn mainColumn = null;// 主键
	private List<CodeColumn> columns;// 表中的列

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComments() {
		return tableComments;
	}

	public void setTableComments(String tableComments) {
		this.tableComments = tableComments;
	}

	public String getTableNamePolished() {
		return tableNamePolished;
	}

	public void setTableNamePolished(String tableNamePolished) {
		this.tableNamePolished = tableNamePolished;
	}

	public CodeColumn getMainColumn() {
		return mainColumn;
	}

	public void setMainColumn(CodeColumn mainColumn) {
		this.mainColumn = mainColumn;
	}

	public List<CodeColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<CodeColumn> columns) {
		this.columns = columns;
	}

	public int getColumnSize() {
		return null == columns ? 0 : columns.size();
	}
}
