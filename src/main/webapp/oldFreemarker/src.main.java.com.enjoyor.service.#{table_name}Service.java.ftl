package ${package}.service;

import java.util.List;
import java.util.Map;

import com.enjoyor.pojo.${table_name};

public interface ${table_name}Service {

	public ${table_name} get(${primary_type} ${j_primary_key});
	
	public List<${table_name}> getList(Map<String, Object> map);
	
	public Page getPage(Map map, Page page);
	
	public int save(${table_name} entity);
	
	public int modify(${table_name} entity);
	
	public int remove(${primary_type} ${j_primary_key});
}