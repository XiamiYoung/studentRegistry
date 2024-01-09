package edu.dufe.student.registry.controller.adminconsole;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.dufe.student.registry.controller.base.AdminBaseController;
import edu.dufe.student.registry.controller.validator.ControllerValidator;
import edu.dufe.student.registry.data.in.LoginData;
import edu.dufe.student.registry.data.in.UserData;
import edu.dufe.student.registry.data.out.DefaultResData;
import edu.dufe.student.registry.data.out.UserResData;
import edu.dufe.student.registry.facade.AdminLoginFacade;

@RestController
@RequestMapping("/admin")
public class AdminConsoleLoginController extends AdminBaseController{
	
	@Resource
	private AdminLoginFacade loginFacade;
	
	@RequestMapping(value= "/login",method = RequestMethod.POST)
	public UserResData adminLogin(@RequestBody LoginData loginData,HttpServletResponse response) throws Exception {
		ControllerValidator.checkInbound(loginData);
		UserData userData = loginFacade.adminConsoleLogin(loginData);
		UserResData userResData = new UserResData();
		userResData.setUserData(userData);
		
        //add token and aid to header
		String authToken = jwtTokenUtil.generateToken(userData);
		String username = userData.getUsername();
		//JWT header
		addJWTHeader(authToken,username,response);
		
		return userResData;
	}
	
	@RequestMapping(value= "/logout",method = RequestMethod.POST)
	public DefaultResData logout(HttpServletRequest request,HttpServletResponse response) throws Exception {
		DefaultResData defaultResData = new DefaultResData();
		this.invalidToken(request, response);
		return defaultResData;
	}
	
}
