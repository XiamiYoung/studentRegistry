package edu.dufe.student.registry.controller.validator;


import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import edu.dufe.student.registry.constants.Constants;
import edu.dufe.student.registry.constants.ErrorDictionary;
import edu.dufe.student.registry.data.in.LoginData;
import edu.dufe.student.registry.data.in.StudentRegistryData;
import edu.dufe.student.registry.data.in.base.BaseData;
import edu.dufe.student.registry.exception.RestfulException;

public class ControllerValidator {
	public static void checkInbound(Object obj) {
		if(!validateReq(obj)) {
			throw new RestfulException(ErrorDictionary.REQUEST_INVALID);
		}
	}
	
	private static boolean validateReq(Object obj) {
		if(obj==null) {
			return false;
		}
		
		//Validate Request Body
		if(obj instanceof BaseData) {
			//Admin login
			if(obj instanceof LoginData) {
				LoginData loginData = (LoginData)obj;
				if(StringUtils.isEmpty(loginData.getUserName())) {
					return false;
				}
				
				if(StringUtils.isEmpty(loginData.getPassword())) {
					return false;
				}
			}
			
			//Registry submission
			if(obj instanceof StudentRegistryData) {
				StudentRegistryData StudentRegistryData = (StudentRegistryData)obj;
				if(StringUtils.isEmpty(StudentRegistryData.getStudentName())
						||StudentRegistryData.getStudentName().length()>Constants.REGISTRY_STUDENT_NAME_LENGTH
						||!Pattern.matches("^[\u4E00-\u9FA5]*$", StudentRegistryData.getStudentName())){
					return false;
				}
				if(StringUtils.isEmpty(StudentRegistryData.getStudentSex())
						||(!StudentRegistryData.getStudentSex().equals(Constants.REGISTRY_SEX_MALE)
							&&!StudentRegistryData.getStudentSex().equals(Constants.REGISTRY_SEX_FEMALE))){
					return false;
				}
				if(StringUtils.isEmpty(StudentRegistryData.getStudentStudyNum())
						||StudentRegistryData.getStudentStudyNum().length()!=Constants.REGISTRY_STUDENT_STUDY_NUM_LENGTH){
					return false;
				}
				if(StringUtils.isEmpty(StudentRegistryData.getStudentGrade())
						||StudentRegistryData.getStudentGrade().length()>Constants.REGISTRY_STUDENT_GRADE_LENGTH){
					return false;
				}
				if(StringUtils.isEmpty(StudentRegistryData.getStudentMajor())
						||StudentRegistryData.getStudentMajor().length()>Constants.REGISTRY_STUDENT_MAJOR_LENGTH){
					return false;
				}
				if(StringUtils.isEmpty(StudentRegistryData.getStudentIdentityNum())
						||StudentRegistryData.getStudentIdentityNum().length()!=Constants.REGISTRY_IDENTITY_LENGTH){
					return false;
				}
				if(StringUtils.isEmpty(StudentRegistryData.getStudentTel())
						||StudentRegistryData.getStudentTel().length()!=Constants.REGISTRY_TEL_LENGTH){
					return false;
				}
				if(StringUtils.isEmpty(StudentRegistryData.getImageContent())){
					return false;
				}
			}
		}
		//validate basic data type
		else if(obj instanceof String) {
			String val = (String)obj;
			if(StringUtils.isEmpty(val)) {
				return false;
			}
		}
		return true;
	}
	
	public static void checkPkForUpdate(BaseData baseData) {
		if(baseData==null || baseData.getPk()==null || baseData.getPk()<0) {
			throw new RestfulException(ErrorDictionary.UPDATE_ID_NOT_EXIST);
		}
	}
}
