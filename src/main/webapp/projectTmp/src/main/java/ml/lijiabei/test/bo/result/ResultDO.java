package ml.lijiabei.templete.bo.result;

public class ResultDO<T> extends BaseResultDO {

	private T result;

	public void setResult(T result) {
		this.result = result;
	}

	public T getResult() {
		return this.result;
	}
}