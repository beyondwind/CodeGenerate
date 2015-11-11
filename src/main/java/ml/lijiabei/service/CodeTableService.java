package ml.lijiabei.service;

import java.util.List;
import java.util.Map;

import ml.lijiabei.entity.CodeTable;

public interface CodeTableService {

	public List<CodeTable> getList(Map<String, Object> map);
}
