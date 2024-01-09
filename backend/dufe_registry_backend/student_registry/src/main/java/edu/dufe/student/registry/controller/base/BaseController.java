package edu.dufe.student.registry.controller.base;



import edu.dufe.student.registry.constants.ErrorDictionary;
import edu.dufe.student.registry.exception.RestfulException;

public abstract class BaseController {
	
	protected void failRes(ErrorDictionary errorDicItem){
		throw new RestfulException(errorDicItem);
	}
	
}
