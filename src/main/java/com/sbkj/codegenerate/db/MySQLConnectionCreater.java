package com.sbkj.codegenerate.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sbkj.codegenerate.error.DBException;
import com.sbkj.codegenerate.utils.SelectSQLs;

public class MySQLConnectionCreater implements ConnectionCreater {

	private static final String mysqlUrl = "jdbc:mysql://";
	private static final String jdbcDriverClassName = "com.mysql.jdbc.Driver";

	@Override
	public Connection createConnection(String ip, int port, String dbName, String userName, String password) throws DBException {
		return createConnection(ip, port, dbName, userName, password, "UTF8");
	}

	@Override
	public Connection createConnection(String ip, int port, String dbName, String userName, String password, String characterEncoding) throws DBException {
		Connection connection = null;
		try {
			Class.forName(jdbcDriverClassName);// 动态加载mysql驱动
			StringBuffer sb = new StringBuffer(mysqlUrl);
			sb.append(ip).append(":").append(port).append("/information_schema?useUnicode=true&characterEncoding=").append(characterEncoding);
			connection = DriverManager.getConnection(sb.toString(), userName, password);
		} catch (ClassNotFoundException e) {
			throw new DBException("com.mysql.jdbc.Driver载入失败！", e);
		} catch (SQLException e) {
			throw new DBException("mysql connection 生成失败！", e);
		}
		return connection;
	}

	@Override
	public String getQueryTablesSQL() {
		return SelectSQLs.MYSQL_QUERY_TABLES;
	}

	@Override
	public String getQueryColumnsSQL() {
		return SelectSQLs.MYSQL_QUERY_COLUMNS;
	}

}
