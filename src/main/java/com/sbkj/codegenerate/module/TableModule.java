package com.sbkj.codegenerate.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sbkj.codegenerate.bo.GenerateInfo;
import com.sbkj.codegenerate.db.ConnectionCreater;
import com.sbkj.codegenerate.db.MySQLConnectionCreater;
import com.sbkj.codegenerate.domain.CodeColumn;
import com.sbkj.codegenerate.domain.CodeTable;
import com.sbkj.codegenerate.error.DBException;
import com.sbkj.codegenerate.error.TableModuleException;
import com.sbkj.codegenerate.utils.GenerateConstants;

public class TableModule {

	private ConnectionCreater connectionCreater;

	/**
	 * @Title: queryCodeTableList
	 * @Description: 获取表列表
	 * @param generateInfo
	 * @return List<CodeTable> 返回类型
	 * @throws TableModuleException
	 * @throws DBException
	 */
	public List<CodeTable> queryCodeTableList(GenerateInfo generateInfo) throws TableModuleException, DBException {
		List<CodeTable> list = null;
		switch (generateInfo.getDatabaseType()) {
			case GenerateConstants.TYPE_MYSQL:
				connectionCreater = new MySQLConnectionCreater();
				break;
			case GenerateConstants.TYPE_ORACLE:
				throw new TableModuleException("目前不支持Oracle");
			default:
				throw new TableModuleException("请选择正确数据库类型！");
		}
		// 创建数据库连接
		Connection con = connectionCreater.createConnection(generateInfo.getIpAddress(), generateInfo.getIntegerPort(), generateInfo.getDbName(), generateInfo.getUserName(),
				generateInfo.getPassword());
		try {
			// 执行动态sql
			PreparedStatement pstmt = con.prepareStatement(connectionCreater.getQueryTablesSQL());
			pstmt.setString(1, generateInfo.getDbName());
			ResultSet rs = pstmt.executeQuery();

			// 封装结果
			list = new ArrayList<CodeTable>();
			CodeTable codeTable = null;
			while (rs.next()) {
				codeTable = new CodeTable();
				codeTable.setTableName(rs.getString(1));
				codeTable.setTableComments(rs.getString(2));
				list.add(codeTable);
			}

			if (rs != null) { // 关闭记录集
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DBException("记录集关闭异常！", e);
				}
			}
			if (pstmt != null) { // 关闭声明
				try {
					pstmt.close();
				} catch (SQLException e) {
					throw new DBException("PreparedStatement关闭异常！", e);
				}
			}
			if (con != null) { // 关闭连接对象
				try {
					con.close();
				} catch (SQLException e) {
					throw new DBException("数据库连接关闭异常！", e);
				}
			}
		} catch (Exception e) {
			throw new TableModuleException("数据库查询出错！", e);
		}
		return list;
	}

	public List<CodeColumn> queryCodeColumnList(GenerateInfo generateInfo, String tableName) throws TableModuleException, DBException {
		List<CodeColumn> list = null;
		switch (generateInfo.getDatabaseType()) {
			case GenerateConstants.TYPE_MYSQL:
				connectionCreater = new MySQLConnectionCreater();
				break;
			case GenerateConstants.TYPE_ORACLE:
				throw new TableModuleException("目前不支持Oracle");
			default:
				throw new TableModuleException("请选择正确数据库类型！");
		}
		// 创建数据库连接
		Connection con = connectionCreater.createConnection(generateInfo.getIpAddress(), generateInfo.getIntegerPort(), generateInfo.getDbName(), generateInfo.getUserName(),
				generateInfo.getPassword());
		try {
			// 执行动态sql
			PreparedStatement pstmt = con.prepareStatement(connectionCreater.getQueryColumnsSQL());
			pstmt.setString(1, tableName);
			pstmt.setString(2, generateInfo.getDbName());
			ResultSet rs = pstmt.executeQuery();

			// 封装结果
			list = new ArrayList<CodeColumn>();
			CodeColumn codeColumn = null;
			while (rs.next()) {
				codeColumn = new CodeColumn();
				codeColumn.setColumnName(rs.getString(1));
				codeColumn.setColumnType(rs.getString(2));
				codeColumn.setColumnComments(rs.getString(3));
				codeColumn.setIsPrimary(rs.getString(4));
				list.add(codeColumn);
			}

			if (rs != null) { // 关闭记录集
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DBException("记录集关闭异常！", e);
				}
			}
			if (pstmt != null) { // 关闭声明
				try {
					pstmt.close();
				} catch (SQLException e) {
					throw new DBException("PreparedStatement关闭异常！", e);
				}
			}
			if (con != null) { // 关闭连接对象
				try {
					con.close();
				} catch (SQLException e) {
					throw new DBException("数据库连接关闭异常！", e);
				}
			}
		} catch (Exception e) {
			throw new TableModuleException("数据库查询出错！", e);
		}
		return list;
	}

}
