package com.enjoyor.common.util;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.builder.xml.dynamic.ForEachSqlNode;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author : wxq
 * @date : 2012-8-28 下午2:46:06
 * 
 **/
public class MyBatisSqlUtils {
	/**
	 * 运行期获取MyBatis执行的SQL及参数
	 * 
	 * @param id Mapper xml 文件里的select Id
	 * @param parameterMap 参数
	 * @param sqlSessionFactory
	 * @return
	 */
	public static MyBatisSql getMyBatisSql(String id, Map<String, Object> parameterMap, SqlSessionFactory sqlSessionFactory) {
		MyBatisSql ibatisSql = new MyBatisSql();
		MappedStatement ms = sqlSessionFactory.getConfiguration().getMappedStatement(id);
		BoundSql boundSql = ms.getBoundSql(parameterMap);
		ibatisSql.setSql(boundSql.getSql());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Object[] parameterArray = new Object[parameterMappings.size()];
			ParameterMapping parameterMapping = null;
			Object value = null;
			Object parameterObject = null;
			MetaObject metaObject = null;
			PropertyTokenizer prop = null;
			String propertyName = null;
			String[] names = null;
			for (int i = 0; i < parameterMappings.size(); i++) {
				parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					propertyName = parameterMapping.getProperty();
					names = propertyName.split("\\.");
					if (propertyName.indexOf(".") != -1 && names.length == 2) {
						parameterObject = parameterMap.get(names[0]);
						propertyName = names[1];
					} else if (propertyName.indexOf(".") != -1 && names.length == 3) {
						parameterObject = parameterMap.get(names[0]); // map
						if (parameterObject instanceof Map) {
							parameterObject = ((Map) parameterObject).get(names[1]);
						}
						propertyName = names[2];
					} else {
						parameterObject = parameterMap.get(propertyName);
					}
					metaObject = parameterMap == null ? null : MetaObject.forObject(parameterObject);
					prop = new PropertyTokenizer(propertyName);
					if (!propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && parameterObject == null) {
						value = null;
					} else if (parameterObject != null && ms.getConfiguration().getTypeHandlerRegistry().hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = MetaObject.forObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					parameterArray[i] = value;
				}
			}
			ibatisSql.setParameters(parameterArray);
		}
		return ibatisSql;
	}
}
