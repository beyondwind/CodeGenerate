package com.sbkj.codegenerate.utils;

public enum OracleType {
	LONG("long"), NUMBER("float"), DATE("Date"), VARCHAR2("String"), NVARCHAR2(
			"String"), CHAR("String"), BLOB("byte[]");
	private String javaType;

	private OracleType() {
	}

	private OracleType(String javaType) {
		this.javaType = javaType;
	}

	public String getJavaType() {
		return javaType;
	}
}
