package edu.dufe.student.registry.constants;

public class Constants {
	//HTTP CODE
	public static final int HTTP_CODE_200 = 200;
	public static final int HTTP_CODE_400 = 400;
	public static final int HTTP_CODE_401 = 401;
	public static final int HTTP_CODE_403 = 403;
	public static final int HTTP_CODE_404 = 404;
	public static final int HTTP_CODE_405 = 405;
	public static final int HTTP_CODE_500 = 500;
	
	//Default Success Mesage
	public static final String DEFAULT_SUC_MESSAGE = "Operation Successful";
	
	//reason code
	public static final String SUCCESS_CODE = "DSR000";
	
	//error category
	public static final String PREFIX_REST = "DSR";
	public static final String ERROR_TYPE_COMMON = "1";
	public static final String ERROR_TYPE_LOGIN = "2";
	public static final String ERROR_TYPE_USER = "3";
	public static final String ERROR_TYPE_REGISTRY = "4";
	public static final String ERROR_TYPE_FILE = "5";
	public static final String ERROR_TYPE_BULLETIN = "6";
	public static final String ERROR_TYPE_SITEMANAGE = "7";
	
	//timestmp 
	public static final String DEFAULT_LOCALE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	//HTTP header
	public static final String CONTENT_TYPE_JSON = "application/json";
	
	//Registry schema
	public static final String REGISTRY_SEX_MALE = "男";
	public static final String REGISTRY_SEX_FEMALE = "女";
	public static final int REGISTRY_STUDENT_NAME_LENGTH = 10;
	public static final int REGISTRY_STUDENT_GRADE_LENGTH = 4;
	public static final int REGISTRY_STUDENT_MAJOR_LENGTH = 30;
	public static final int REGISTRY_IDENTITY_LENGTH = 18;
	public static final int REGISTRY_STUDENT_STUDY_NUM_LENGTH = 12;
	public static final int REGISTRY_TEL_LENGTH = 11;
	
	//image
	public static final String ImageSuffix = ".jpg";
}
