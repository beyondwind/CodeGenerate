package com.sbkj.codegenerate.bo;

/**
 * @ClassName: GenerateInfo
 * @Description: 生成代码需要的信息
 * @author lijiabei
 * @date 2015年3月26日 上午10:54:28
 */
public class GenerateInfo {

	private String ipAddress;
	private String port;
	private String dbName;
	private String userName;
	private String password;
	private int databaseType;
	private String packagePath;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getIntegerPort() {
		return Integer.valueOf(port);
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(int databaseType) {
		this.databaseType = databaseType;
	}

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}
}
