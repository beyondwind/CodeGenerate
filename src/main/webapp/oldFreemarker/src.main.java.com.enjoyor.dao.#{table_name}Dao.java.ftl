package ${package}.dao;

import java.util.List;
import java.util.Map;

import com.enjoyor.common.Page;
import com.enjoyor.pojo.${table_name};

public interface ${table_name}Dao {

	public ${table_name} query(${primary_type} ${j_primary_key});
	
	public List<${table_name}> queryList(Map<String, Object> map);
	
	public Page queryPage(Map map, Page page);
	
	public int insert(${table_name} entity);
	
	public int update(${table_name} entity);
	
	public int delete(${primary_type} ${j_primary_key});

}