package edu.dufe.student.registry.exception;

import edu.dufe.student.registry.constants.ErrorDictionary;
import edu.dufe.student.registry.data.error.ErrorData;

public class RestfulException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ErrorData errorData;
	
	public RestfulException(ErrorDictionary enumItem){
		super(ErrorDictionary.getError(enumItem).getMsg());
		this.errorData = ErrorDictionary.getError(enumItem);
	}
	
	public RestfulException(ErrorDictionary enumItem, String[] params){
		super(ErrorDictionary.getError(enumItem,params).getMsg());
		this.errorData = ErrorDictionary.getError(enumItem,params);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ErrorData getErrorData() {
		return errorData;
	}

	public void setErrorData(ErrorData errorData) {
		this.errorData = errorData;
	}
	
	
}
