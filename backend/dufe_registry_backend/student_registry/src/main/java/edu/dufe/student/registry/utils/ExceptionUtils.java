package edu.dufe.student.registry.utils;

import edu.dufe.student.registry.data.error.ErrorData;
import edu.dufe.student.registry.data.error.ErrorResData;

public class ExceptionUtils {
	public static ErrorResData getErrorResData(ErrorData errorData) {
		ErrorResData errorResData = new ErrorResData();
		errorResData.setMsg(errorData.getMsg());
		errorResData.setReasonCode(errorData.getReasonCode());
		return errorResData;
	}
}
