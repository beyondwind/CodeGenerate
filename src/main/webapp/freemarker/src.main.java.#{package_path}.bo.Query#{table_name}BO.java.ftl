package ${package}.bo;

import java.util.Date;
import com.shangban.common.bo.BaseQueryBO;

/**
 * @ClassName: ${table_name}查询BO
 * @Description: by CodeGenerate
 * @author lijiabei
 * @date ${.now?date}
 * 
 */
public class Query${table_name}BO extends BaseQueryBO{

	<#list table_columns as column>
	private ${column.dataType} ${column.dataName};// ${column.columnComments} <#if column.isPrimary = "1">( 主键 )</#if> 
	</#list>
	
	<#list table_columns as column>
	public void set${column.dataName?cap_first}(${column.dataType} ${column.dataName}){
		this.${column.dataName} = ${column.dataName};
	}

	public ${column.dataType} get${column.dataName?cap_first}(){
		return ${column.dataName};
	}

	</#list>
}