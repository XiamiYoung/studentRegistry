package edu.dufe.student.registry.facade;

import edu.dufe.student.registry.data.in.LoginData;
import edu.dufe.student.registry.data.in.UserData;

public interface AdminLoginFacade {
	public UserData adminConsoleLogin(LoginData loginData);
}
