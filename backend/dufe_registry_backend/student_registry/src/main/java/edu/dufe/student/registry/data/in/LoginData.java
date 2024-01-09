package edu.dufe.student.registry.data.in;

import edu.dufe.student.registry.data.in.base.BaseData;

public class LoginData extends BaseData{
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
