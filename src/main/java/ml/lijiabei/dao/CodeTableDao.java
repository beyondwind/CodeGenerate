package ml.lijiabei.dao;

import java.util.List;
import java.util.Map;

import ml.lijiabei.entity.CodeTable;

public interface CodeTableDao {

	public List<CodeTable> queryList(Map<String, Object> map);

}