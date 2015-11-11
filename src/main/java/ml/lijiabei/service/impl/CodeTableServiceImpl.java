package ml.lijiabei.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ml.lijiabei.dao.CodeTableDao;
import ml.lijiabei.entity.CodeTable;
import ml.lijiabei.service.CodeTableService;

@Service
public class CodeTableServiceImpl implements CodeTableService {

	@Autowired
	private CodeTableDao codeTableDao;

	@Override
	public List<CodeTable> getList(Map<String, Object> map) {
		return codeTableDao.queryList(map);
	}

}
