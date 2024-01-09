package edu.dufe.student.registry.exception.handler;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.dufe.student.registry.constants.Constants;
import edu.dufe.student.registry.data.error.ErrorData;
import edu.dufe.student.registry.data.error.ErrorResData;
import edu.dufe.student.registry.exception.RestfulException;
import edu.dufe.student.registry.utils.ExceptionUtils;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Value("${tracelog.print}")
	private boolean isPrintStackTrace;
	
	@ExceptionHandler(RestfulException.class)
	@ResponseBody
    public ErrorResData restExceptionHandler(RestfulException e,HttpServletResponse response) {
		if(isPrintStackTrace) {
			e.printStackTrace();
		}
		
		ErrorData errorData = e.getErrorData();
		ErrorResData errorResData = ExceptionUtils.getErrorResData(errorData);
		response.setStatus(e.getErrorData().getHttpCode());
        return errorResData;
    }
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
    public ErrorResData runtimeExceptionHandler(RuntimeException e,HttpServletResponse response) {
		if(isPrintStackTrace) {
			e.printStackTrace();
		}
		
		response.setStatus(Constants.HTTP_CODE_500);
		
		ErrorResData errorResData = new ErrorResData();
		errorResData.setReasonCode(Constants.PREFIX_REST + Constants.HTTP_CODE_500);
		errorResData.setMsg(e.getMessage());
        return errorResData;
    }
}
