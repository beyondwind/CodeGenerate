package com.sbkj.codegenerate.utils;

public enum MySqlType {

	BIGINT("Long"), DATE("Date"), DATETIME("Date"), DECIMAL("double"), DOUBLE(
			"double"), FLOAT("float"), INT("Integer"), TIMESTAMP("Date"), SMALLINT("int"), 
			TINYINT("int"), MEDIUMTEXT("String"), TEXT("String"), CHAR("String"), VARCHAR(
			"String");

	private String javaType;

	private MySqlType() {
	}

	private MySqlType(String javaType) {
		this.javaType = javaType;
	}

	public String getJavaType() {
		return javaType;
	}
}
