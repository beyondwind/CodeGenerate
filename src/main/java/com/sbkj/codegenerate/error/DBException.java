package com.sbkj.codegenerate.error;

public class DBException extends Exception {

	private static final long serialVersionUID = 7166140490713056264L;

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public DBException(String msg){
		super(msg, null);
		this.msg = msg;
	}

	public DBException(String msg, Throwable cause){
		super(msg, cause);
		this.msg = msg;
	}

	public DBException(Throwable cause){
		super("", cause);
	}

}
