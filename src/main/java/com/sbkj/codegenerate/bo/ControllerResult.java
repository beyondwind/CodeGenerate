package com.sbkj.codegenerate.bo;

import java.io.Serializable;

/**
 * @ClassName: ControllerResult
 * @Description: ControllerResult层向页面返回的信息
 * @author lijiabei
 * @date 2013-11-20 上午11:27:04
 */
public class ControllerResult implements Serializable {

	private static final long serialVersionUID = 161389017990134747L;
	// 操作结果标志
	private boolean success = true;
	// 返回信息
	private String msg;
	// service层返回对象
	private Object dataObject;
	// 消息code
	private String msgCode;

	public Object getDataObject() {
		return dataObject;
	}

	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}

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

	public ControllerResult(boolean success, String msg){
		super();
		this.success = success;
		this.msg = msg;
	}

	public ControllerResult(){
		super();
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

}
