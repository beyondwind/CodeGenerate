package ${package}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import ${package}.bo.Query${table_name}BO;
import ${package}.bo.result.BaseResultDO;
import ${package}.bo.result.ResultDO;
import ${package}.bo.result.ResultList;
import ${package}.dao.${table_name}Dao;
import ${package}.domain.${table_name}DO;
import ${package}.service.${table_name}Service;

@Service
public class ${table_name}ServiceImpl implements ${table_name}Service {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private ${table_name}Dao ${table_name?uncap_first}Dao;

	@Override
	public ResultDO<${table_name}DO> query${table_name}ById(Query${table_name}BO query) {
		ResultDO<${table_name}DO> resultDO = new ResultDO<${table_name}DO>();
		${table_name} ${table_name?uncap_first} = null;
		resultDO.setSuccess(true);
		try {
			${table_name?uncap_first} = ${table_name?uncap_first}Dao.select${table_name}ById(query);
			resultDO.setResult(${table_name?uncap_first});
		} catch (Exception e) {
			resultDO.setSuccess(false);
			resultDO.setMsg("${table_name} query${table_name}ById error");
			log.error("${table_name}ServiceImpl query${table_name}ById error", e);
		}
		return resultDO;
	}

	@Override
	public ResultList<${table_name}DO> query${table_name}List(Query${table_name}BO query) {
		ResultList<${table_name}DO> resultList = new ResultList<${table_name}DO>();
		List<${table_name}DO> list = null;
		resultList.setSuccess(true);
		try {
			list = ${table_name?uncap_first}Dao.select${table_name}List(query);
			resultList.setResult(list);
		} catch (Exception e) {
			resultList.setSuccess(false);
			resultList.setMsg("${table_name} query${table_name}List error");
			log.error("${table_name}ServiceImpl query${table_name}List error", e);
		}
		return resultList;
	}

	@Override
	public BaseResultDO create${table_name}(${table_name}DO ${table_name?uncap_first}) {
		BaseResultDO baseResultDO = new BaseResultDO();
		int result = 0;
		baseResultDO.setSuccess(true);
		try {
			result = ${table_name?uncap_first}Dao.insert${table_name}(${table_name?uncap_first});
			if (result < 1) {
				baseResultDO.setSuccess(false);
			}
		} catch (Exception e) {
			baseResultDO.setSuccess(false);
			baseResultDO.setMsg("${table_name} create${table_name} error");
			log.error("${table_name}ServiceImpl create${table_name} error", e);
		}
		return baseResultDO;
	}

	@Override
	public BaseResultDO modify${table_name}(${table_name}DO ${table_name?uncap_first}) {
		BaseResultDO baseResultDO = new BaseResultDO();
		int result = 0;
		baseResultDO.setSuccess(true);
		try {
			result = ${table_name?uncap_first}Dao.update${table_name}(${table_name?uncap_first});
			if (result < 1) {
				baseResultDO.setSuccess(false);
			}
		} catch (Exception e) {
			baseResultDO.setSuccess(false);
			baseResultDO.setMsg("${table_name} modify${table_name} error");
			log.error("${table_name}ServiceImpl modify${table_name} error", e);
		}
		return baseResultDO;
	}

	@Override
	public BaseResultDO remove${table_name}(${table_name}DO ${table_name?uncap_first}) {
		BaseResultDO baseResultDO = new BaseResultDO();
		int result = 0;
		baseResultDO.setSuccess(true);
		try {
			result = ${table_name?uncap_first}Dao.delete${table_name}(${table_name?uncap_first});
			if (result < 1) {
				baseResultDO.setSuccess(false);
			}
		} catch (Exception e) {
			baseResultDO.setSuccess(false);
			baseResultDO.setMsg("${table_name} remove${table_name} error");
			log.error("${table_name}ServiceImpl remove${table_name} error", e);
		}
		return baseResultDO;
	}

	public void set${table_name}Dao(${table_name}Dao ${table_name?uncap_first}Dao) {
		this.${table_name?uncap_first}Dao = ${table_name?uncap_first}Dao;
	}
}