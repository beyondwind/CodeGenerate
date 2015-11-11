package com.sbkj.codegenerate.error;

public class ProcessException extends Exception {

	private static final long serialVersionUID = 7166140490713056264L;

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ProcessException(String msg){
		super(msg, null);
		this.msg = msg;
	}

	public ProcessException(String msg, Throwable cause){
		super(msg, cause);
		this.msg = msg;
	}

	public ProcessException(Throwable cause){
		super("", cause);
	}

}
