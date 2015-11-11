package com.enjoyor.common.util;

public class OracleDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		
		sql = sql.trim();
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ where rownum <= ").append(offset + limit).append(" ) where rownum_ > ").append(offset);
		return pagingSelect.toString();
	}

}
