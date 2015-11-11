package ml.lijiabei.templete.bo.result;

import java.util.List;

public class ResultList<T> extends BaseResultDO {

	private List<T> result;

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
}