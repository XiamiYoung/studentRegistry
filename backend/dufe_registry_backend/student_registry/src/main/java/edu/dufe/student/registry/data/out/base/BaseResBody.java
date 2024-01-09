package edu.dufe.student.registry.data.out.base;

import edu.dufe.student.registry.constants.Constants;

public abstract class BaseResBody {
	private String msg = Constants.DEFAULT_SUC_MESSAGE;
	private String reasonCode = Constants.SUCCESS_CODE;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	
}
