package ml.lijiabei.entity;

import java.util.List;

public class CodeTable {
	private String table_name;// 表名
	private String table_comments;// 表备注

	private List<CodeColumn> columns;// 表中的列

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getTable_comments() {
		return table_comments;
	}

	public void setTable_comments(String table_comments) {
		this.table_comments = table_comments;
	}

	public List<CodeColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<CodeColumn> columns) {
		this.columns = columns;
	}
}
