package edu.dufe.student.registry.constants;

import edu.dufe.student.registry.data.error.ErrorData;

public enum ErrorDictionary {
	// common error
	REQUEST_INVALID(Constants.PREFIX_REST + Constants.ERROR_TYPE_COMMON + "001", Constants.HTTP_CODE_400,
			"Invalid request body."),
	DATA_NOT_SYNC(Constants.PREFIX_REST + Constants.ERROR_TYPE_COMMON + "002", Constants.HTTP_CODE_400,
			"Request data not in sync."),
	UPDATE_ID_NOT_EXIST(Constants.PREFIX_REST + Constants.ERROR_TYPE_COMMON + "003", Constants.HTTP_CODE_400,
			"Request data does not contain valid PK."),
	RESOURCE_NOT_ACCCESSABLE(Constants.PREFIX_REST + Constants.ERROR_TYPE_COMMON + "004", Constants.HTTP_CODE_400,
			"Request resource is not accessable by current user."),

	// login error
	LOGIN_AD(Constants.PREFIX_REST + Constants.ERROR_TYPE_LOGIN + "001", Constants.HTTP_CODE_401,
			"Failed to authenticate user"),
	
	SESSION_TIMEOUT(Constants.PREFIX_REST + Constants.ERROR_TYPE_LOGIN + "002", Constants.HTTP_CODE_401,
			"Session expired"),
	
	TOKEN_INVALID(Constants.PREFIX_REST + Constants.ERROR_TYPE_LOGIN + "003", Constants.HTTP_CODE_401,
			"Token invalid"),

	// user error
	USER_NOT_AUTHENTICATED(Constants.PREFIX_REST + Constants.ERROR_TYPE_USER + "001", Constants.HTTP_CODE_403,
			"Authentication failure"),
	
	// registry error
	REGISTRY_IDENDITY_DUPLICATED(Constants.PREFIX_REST + Constants.ERROR_TYPE_REGISTRY + "001", Constants.HTTP_CODE_403,
			"Identity/Exam Num duplicated"),
	
	// file error
	IMAGE_FAILED_TO_CREATE(Constants.PREFIX_REST + Constants.ERROR_TYPE_FILE + "001", Constants.HTTP_CODE_403,
			"Failed to save image"),
	
	IMAGE_FAILED_TO_READ(Constants.PREFIX_REST + Constants.ERROR_TYPE_FILE + "002", Constants.HTTP_CODE_403,
			"Failed to read image"),
	
	IMAGE_FAILED_TO_DELETE(Constants.PREFIX_REST + Constants.ERROR_TYPE_FILE + "003", Constants.HTTP_CODE_403,
			"Failed to delete image"),
	
	NO_IMAGE_FILE_FOUND(Constants.PREFIX_REST + Constants.ERROR_TYPE_FILE + "004", Constants.HTTP_CODE_403,
			"No image file is found"),
	
	ZIP_FAILED_TO_CREATE(Constants.PREFIX_REST + Constants.ERROR_TYPE_FILE + "005", Constants.HTTP_CODE_403,
			"Failed to create zip files"),
	
	NO_ZIP_FILE_FOUND(Constants.PREFIX_REST + Constants.ERROR_TYPE_FILE + "006", Constants.HTTP_CODE_403,
			"No zip file is found"),
	
	ZIP_FAILED_TO_DOWNLOAD(Constants.PREFIX_REST + Constants.ERROR_TYPE_FILE + "007", Constants.HTTP_CODE_403,
			"Failed to download zip files"),
	
	// bulletin error
	BULLETIN_IDENDITY_DUPLICATED(Constants.PREFIX_REST + Constants.ERROR_TYPE_BULLETIN + "001", Constants.HTTP_CODE_403,
			"Bulletin record duplicated"),
	
	// site management error
	SITE_MANAGE_IDENDITY_DUPLICATED(Constants.PREFIX_REST + Constants.ERROR_TYPE_SITEMANAGE + "001", Constants.HTTP_CODE_403,
			"Site management record duplicated"),
	
	SITE_MANAGE_SITE_CLOSED(Constants.PREFIX_REST + Constants.ERROR_TYPE_SITEMANAGE + "002", Constants.HTTP_CODE_403,
			"Site closed"),
	
	SITE_MANAGE_FAILED_TO_DELETE_REGISTRY_DATA(Constants.PREFIX_REST + Constants.ERROR_TYPE_SITEMANAGE + "003", Constants.HTTP_CODE_403,
			"Failed to clear user registry data");

	String reasonCode;
	int httpCode;
	String message;

	ErrorDictionary(String reasonCode, int httpCode, String message) {
		this.reasonCode = reasonCode;
		this.httpCode = httpCode;
		this.message = message;
	}

	public static ErrorData getError(ErrorDictionary enumItem) {
		ErrorData errorData = new ErrorData();
		errorData.setHttpCode(enumItem.getHttpCode());
		errorData.setMsg(enumItem.getMessage());
		errorData.setReasonCode(enumItem.getReasonCode());
		return errorData;
	}
	
	public static ErrorData getError(ErrorDictionary enumItem, String[] params) {
		ErrorData errorData = new ErrorData();
		errorData.setHttpCode(enumItem.getHttpCode());
		String convertedMsg = String.format(enumItem.getMessage(), params);
		errorData.setMsg(convertedMsg);
		errorData.setReasonCode(enumItem.getReasonCode());
		return errorData;
	}

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
