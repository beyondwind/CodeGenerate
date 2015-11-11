package com.sbkj.codegenerate.domain;

public class CodeColumn {

	private String columnName;// 列名
	private String columnType;// 列属性
	private String columnComments;// 列备注
	private String isPrimary;// 1是主键，0非主键
	private String dataName;// 转化成java对应的列名
	private String dataType;// 转化成java对应的类型

	public CodeColumn(){
		super();
	}

	public CodeColumn(CodeColumn codeColumn){
		super();
		this.columnName = codeColumn.getColumnName();
		this.columnType = codeColumn.getColumnType();
		this.columnComments = codeColumn.getColumnComments();
		this.isPrimary = codeColumn.getIsPrimary();
		this.dataName = codeColumn.getDataName();
		this.dataType = codeColumn.getDataType();
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnComments() {
		return columnComments;
	}

	public void setColumnComments(String columnComments) {
		this.columnComments = columnComments;
	}

	public String getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}
