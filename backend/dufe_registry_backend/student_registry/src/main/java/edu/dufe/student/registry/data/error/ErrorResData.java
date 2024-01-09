package edu.dufe.student.registry.data.error;

import edu.dufe.student.registry.data.out.base.BaseResBody;

public class ErrorResData extends BaseResBody{
	
	private String reasonCode;
	
	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
}
