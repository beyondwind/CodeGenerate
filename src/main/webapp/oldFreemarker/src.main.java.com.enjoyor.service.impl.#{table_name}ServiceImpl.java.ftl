package ${package}.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyor.common.Page;
import com.enjoyor.pojo.${table_name};
import com.enjoyor.dao.${table_name}Dao;
import com.enjoyor.service.${table_name}Service;

@Service
public class ${table_name}Service {

	@Autowired
	private ${table_name}Dao dao;

	public ${table_name} get(${primary_type} ${j_primary_key}){
		return dao.query(${j_primary_key});
	}
	
	public List<${table_name}> getList(Map<String, Object> map){
		return dao.queryList(map);
	}
	
	public Page getPage(Map map, Page page){
		return dao.queryPage(map, page);
	}
	
	public int save(${table_name} entity){
		return dao.add(entity);
	}
	
	public int modify(${table_name} entity){
		return dao.update(entity);
	}
	
	public int remove(${primary_type} ${j_primary_key}){
		return dao.delete(${j_primary_key});
	}
}