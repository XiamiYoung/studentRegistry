package edu.dufe.student.registry.facade.impl;

import javax.annotation.Resource;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.dufe.student.registry.constants.ErrorDictionary;
import edu.dufe.student.registry.converter.UserConverter;
import edu.dufe.student.registry.data.in.LoginData;
import edu.dufe.student.registry.data.in.UserData;
import edu.dufe.student.registry.exception.RestfulException;
import edu.dufe.student.registry.facade.AdminLoginFacade;
import edu.dufe.student.registry.service.UserService;
import edu.dufe.student.registry.utils.JwtTokenUtil;

@Service
public class AdminLoginFacadeImpl implements AdminLoginFacade {

	@Resource
	private UserService userService;
	
	@Resource
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	public UserData adminConsoleLogin(LoginData loginData) {
		//check if user exists
		UserData userData = (UserData) userService.loadUserByUsername(loginData.getUserName());
		if(userData==null||userData.getPk()==null) {
			throw new RestfulException(ErrorDictionary.USER_NOT_AUTHENTICATED);
		}else {			
			//authenticate user
			userData = (UserData) userService.findByUserNameAndPassword(loginData.getUserName(), loginData.getPassword());
			if(userData!=null && userData.getPk()!=null) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userData, null, userData.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
				return userData;
			}else {
				throw new RestfulException(ErrorDictionary.USER_NOT_AUTHENTICATED);
			}
		}
	}

}
