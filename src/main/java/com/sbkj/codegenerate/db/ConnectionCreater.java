package com.sbkj.codegenerate.db;

import java.sql.Connection;

import com.sbkj.codegenerate.error.DBException;

/**
 * @ClassName: ConnectionCreater
 * @Description: 创建数据库连接
 * @author lijiabei
 * @date 2015年3月25日 下午5:43:28
 */
public interface ConnectionCreater {

	/**
	 * @Title: createConnection
	 * @Description: 创建数据库连接
	 * @param ip ip地址
	 * @param port 端口
	 * @param dbName 数据库名称/实例名
	 * @param userName 账号
	 * @param password 密码
	 * @return Connection 数据库连接
	 */
	public Connection createConnection(String ip, int port, String dbName, String userName, String password) throws DBException;

	/**
	 * @Title: createConnection
	 * @Description: 创建数据库连接
	 * @param ip ip地址
	 * @param port 端口
	 * @param dbName 数据库名称/实例名
	 * @param userName 账号
	 * @param password 密码
	 * @param characterEncoding 连接字符集
	 * @return Connection 数据库连接
	 */
	public Connection createConnection(String ip, int port, String dbName, String userName, String password, String characterEncoding) throws DBException;

	public String getQueryTablesSQL();

	public String getQueryColumnsSQL();
}
