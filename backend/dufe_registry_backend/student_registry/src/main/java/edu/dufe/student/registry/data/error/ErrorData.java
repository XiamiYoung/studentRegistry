package edu.dufe.student.registry.data.error;

import edu.dufe.student.registry.data.out.base.BaseResBody;

public class ErrorData extends BaseResBody{
	
	private String reasonCode;
	private int httpCode;
	
	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

}