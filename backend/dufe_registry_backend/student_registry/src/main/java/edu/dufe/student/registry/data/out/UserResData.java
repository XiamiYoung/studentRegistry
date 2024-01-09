package edu.dufe.student.registry.data.out;

import edu.dufe.student.registry.data.in.UserData;
import edu.dufe.student.registry.data.out.base.BaseResBody;

public class UserResData extends BaseResBody {
	private UserData userData;

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	
	
}
