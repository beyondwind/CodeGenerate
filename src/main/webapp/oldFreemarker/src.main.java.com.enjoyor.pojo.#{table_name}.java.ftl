package ${package}.pojo;

/**
 * @ClassName: ${table_name}
 * @Description: by CodeGenerate
 * @author lijiabei
 * @date 2013-11-20
 * 
 */
public class ${table_name} {
	<#list table_columns as column>
	private ${column.data_type} ${column.data_name};// ${column.column_comments} <#if column.is_primary = "1">( 主键 )</#if> 
	</#list>
	
	<#list table_columns as column>
	public void set${column.data_name?cap_first}(${column.data_type} ${column.data_name}){
		this.${column.data_name} = ${column.data_name};
	}
	
	public ${column.data_type} get${column.data_name?cap_first}(){
		return ${column.data_name};
	}
	</#list>
}