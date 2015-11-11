package ${package}.service;

import ${package}.bo.Query${table_name}BO;
import com.shangban.common.dto.BaseResultDTO;
import com.shangban.common.dto.ResultDTO;
import com.shangban.common.dto.BatchResultDTO;
import ${package}.domain.${table_name}DO;

public interface ${table_name}Service {
	public ResultDTO<${table_name}DO> query${table_name}ById(Query${table_name}BO query);

	public BatchResultDTO<${table_name}DO> query${table_name}List(Query${table_name}BO query);

	public BaseResultDTO create${table_name}(${table_name}DO ${table_name?uncap_first});

	public BaseResultDTO modify${table_name}(${table_name}DO ${table_name?uncap_first});

	public BaseResultDTO remove${table_name}(${table_name}DO ${table_name?uncap_first});
}