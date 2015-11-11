package ${package}.mapper;

import java.util.List;
import java.util.Map;

import com.enjoyor.pojo.${table_name};

public interface ${table_name}Mapper {

	public ${table_name} query(${primary_type} ${j_primary_key});
	
	public List<${table_name}> queryList(Map<String, Object> map);
	
	public int insert(${table_name} entity);
	
	public int update(${table_name} entity);
	
	public int delete(${primary_type} ${j_primary_key});
}
