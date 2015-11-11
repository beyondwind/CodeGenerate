package ml.lijiabei.templete.bo.result;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class BaseResultDO {

	private boolean success = true;

	private String msg;

	private transient Exception exception;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Exception getException() {
		return exception;
	}

	public void setErrorTrace(Exception e) {
		this.exception = e;
	}

	public static String exceptionToString(Exception e) {
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
		PrintWriter pw = new PrintWriter(out);
		pw.write(e.getMessage() + "\n");
		e.printStackTrace(pw);
		pw.close();
		return out.toString();
	}
}