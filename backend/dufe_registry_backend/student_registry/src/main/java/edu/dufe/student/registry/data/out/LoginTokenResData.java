package edu.dufe.student.registry.data.out;

import edu.dufe.student.registry.data.out.base.BaseResBody;

public class LoginTokenResData extends BaseResBody {
	
	private String token;
	private String tokenUserName;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenUserName() {
		return tokenUserName;
	}

	public void setTokenUserName(String tokenUserName) {
		this.tokenUserName = tokenUserName;
	}

}
