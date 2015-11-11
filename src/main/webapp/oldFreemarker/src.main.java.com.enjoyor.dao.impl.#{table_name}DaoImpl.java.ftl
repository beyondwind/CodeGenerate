package ${package}.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enjoyor.common.Page;
import com.enjoyor.dao.${table_name}Dao;
import com.enjoyor.dao.base.SqlSessionDaoSupport;
import com.enjoyor.mapper.${table_name}Mapper;
import com.enjoyor.pojo.${table_name};

@Repository
public class ${table_name}DaoImpl extends SqlSessionDaoSupport implements
	${table_name}Dao {

	@Autowired
	private ${table_name}Mapper mapper;

	public ${table_name} query(${primary_type} ${j_primary_key}){
		return mapper.query(${j_primary_key});
	}
	
	public List<${table_name}> queryList(Map<String, Object> map){
		return mapper.queryList(map);
	}
	
	public Page queryPage(Map map, Page page){
		return this.queryPageInfo("queryPage", map, page);
	}
	
	public int insert(${table_name} entity){
		return mapper.insert(entity);
	}
	
	public int update(${table_name} entity){
		return mapper.update(entity);
	}
	
	public int delete(${primary_type} ${j_primary_key}){
		return mapper.delete(${j_primary_key});
	}
}