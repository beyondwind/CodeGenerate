<?xml version="1.0" encoding="UTF-8" ?> 
<!DOCTYPE mapper 
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package}.dao.${table_name}Dao">
	<resultMap type="${package}.domain.${table_name}DO" id="${table_name}Result">
		<id property="${j_primary_key}" column="${primary_key}" />
		<#list table_columns as column><#if column.isPrimary != "1">
		<result property="${column.dataName}" column="${column.columnName}" />
		</#if></#list>
	</resultMap>

	<!-- select -->
	<select id="select${table_name}ById" parameterType="${package}.bo.Query${table_name}BO"
		resultMap="${table_name}Result">
		SELECT <#list table_columns as column>${column.columnName}<#if column_has_next>,</#if></#list> FROM ${raw_table} WHERE ${primary_key}=#${r"{"}${j_primary_key}}
	</select>

	<select id="select${table_name}List" parameterType="${package}.bo.Query${table_name}BO"
		resultMap="${table_name}Result">
		SELECT <#list table_columns as column>${column.columnName}<#if column_has_next>,</#if></#list> FROM ${raw_table} LIMIT 100
	</select>

	<!-- insert -->
	<insert id="insert${table_name}" parameterType="${package}.domain.${table_name}DO">
		<selectKey resultType="${primary_type}" keyProperty="${j_primary_key}" order="AFTER">
			SELECT LAST_INSERT_ID()
		</selectKey>
		INSERT INTO
		${raw_table}(${primary_key},<#list table_columns as column><#if column.isPrimary != "1">${column.columnName}<#if column_has_next>,</#if></#if></#list>)
		VALUES(null,<#list table_columns as column><#if column.isPrimary != "1">#${r"{"}${column.dataName}}<#if column_has_next>,</#if></#if></#list>)
	</insert>

	<!-- update -->
	<update id="update${table_name}" parameterType="${package}.domain.${table_name}DO">
		UPDATE ${raw_table} SET
		<#list table_columns as column><#if column.isPrimary != "1">
		${column.columnName}=#${r"{"}${column.dataName}}<#if column_has_next>,</#if>
		</#if></#list>
		WHERE ${primary_key}=#${r"{"}${j_primary_key}}
	</update>

	<!-- delete -->
	<delete id="delete${table_name}" parameterType="${package}.domain.${table_name}DO">
		DELETE FROM ${raw_table}
		WHERE ${primary_key}=#${r"{"}${j_primary_key}}
	</delete>

</mapper>