package com.sbkj.codegenerate.utils;

public interface SelectSQLs {

	/** MYSQL查询表sql 1：TABLE_SCHEMA */
	public static final String MYSQL_QUERY_TABLES = "select t.TABLE_NAME,t.TABLE_COMMENT table_comments from TABLES t where t.TABLE_SCHEMA=? ";
	/** MYSQL查询列sql 1:TABLE_NAME 2：TABLE_SCHEMA */
	public static final String MYSQL_QUERY_COLUMNS = "select lower(c.COLUMN_NAME) column_name,c.DATA_TYPE,c.COLUMN_COMMENT comments,replace(c.COLUMN_KEY,'PRI','1') is_primary from COLUMNS c where c.TABLE_NAME =? and c.TABLE_SCHEMA =? order by c.ORDINAL_POSITION";

	/** ORACLE查询表sql */
	public static final String ORACLE_QUERY_TABLES = "";
	/** ORACLE查询列sql */
	public static final String ORACLE_QUERY_COLUMNS = "";
}
