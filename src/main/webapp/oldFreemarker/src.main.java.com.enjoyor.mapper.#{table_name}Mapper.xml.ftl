<?xml version="1.0" encoding="UTF-8" ?>   
    <!DOCTYPE mapper   
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"   
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${package}.mapper.${table_name}Mapper">

	<resultMap type="${package}.pojo.${table_name}" id="${table_name}Map">
		<id property="${j_primary_key}" column="${primary_key}" />
		<#list table_columns as column>
		<#if column.is_primary != "1">
			<result property="${column.data_name}" column="${column.column_name}" />
		</#if> 
		</#list>
	</resultMap>

	<select id="query" parameterType="${primary_type}" resultMap="${table_name}Map" >
		select * from ${raw_table} where ${primary_key}=#${r"{"}${j_primary_key}}
	</select>

	<select id="queryList" resultType="java.util.List" resultMap="${table_name}Map">
		select * from ${raw_table}
	</select>

	<insert id="insert" parameterType="${package}.pojo.${table_name}">
		insert into ${raw_table}(
		<#list table_columns as column> 
 		 ${column.column_name}<#if column_has_next>,</#if> 
		</#list>
		) values(
		<#list table_columns as column> 
 		 #${r"{"}${column.data_name}}<#if column_has_next>,</#if> 
		</#list>
		)
	</insert>

	<update id="update" parameterType="${package}.pojo.${table_name}">
		update ${raw_table} set
		<#list table_columns as column> 
		<#if column.is_primary != "1"> ${column.column_name}=#${r"{"}${column.data_name}}<#if column_has_next>,</#if>  </#if> 
		</#list>
		where ${primary_key}=#${r"{"}${j_primary_key}}
	</update>

	<delete id="delete" parameterType="${primary_type}">
		delete from ${raw_table} where ${primary_key}=#${r"{"}${j_primary_key}}
	</delete>
</mapper>