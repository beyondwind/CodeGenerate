package ${package}.dao;

import java.util.List;

import ${package}.bo.Query${table_name}BO;
import ${package}.domain.${table_name}DO;

public interface ${table_name}Dao {

	public ${table_name}DO select${table_name}ById(Query${table_name}BO query);

	public List<${table_name}DO> select${table_name}List(Query${table_name}BO query);

	public int insert${table_name}(${table_name}DO ${table_name?uncap_first});

	public int update${table_name}(${table_name}DO ${table_name?uncap_first});

	public int delete${table_name}(${table_name}DO ${table_name?uncap_first});
}