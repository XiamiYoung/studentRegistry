package edu.dufe.student.registry.controller.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;

import edu.dufe.student.registry.utils.HttpUtils;
import edu.dufe.student.registry.utils.JwtTokenUtil;

public class AdminBaseController extends BaseController{
	@Value("${jwt.header.token.name}")
	private String headerName;
	@Value("${jwt.header.user.name}")
    private String headerUserName;
	@Value("${jwt.header.token.header.name}")
    private String tokenHeaderResponse;
	@Value("${jwt.authz.type}")
	private String authzType;
	
	@Resource
    protected JwtTokenUtil jwtTokenUtil;

	protected void addJWTHeader(String authToken,String username, HttpServletResponse response) {
		HttpUtils.addReponseHeader(response,tokenHeaderResponse,authToken);
        HttpUtils.addReponseHeader(response,headerUserName,username);
	}
	
	private String getJWTHeader(HttpServletRequest request) {
		String authToken = "";
		String authHeader = request.getHeader(headerName);

		if (authHeader != null && authHeader.startsWith(authzType)) {
			authToken = authHeader.substring(authzType.length());
			return authToken;
		}
		
		return authToken;
	}
	
	protected void invalidToken(HttpServletRequest request,HttpServletResponse response) {
		String authToken = getJWTHeader(request);
		String invalidToken = jwtTokenUtil.invalidToken(authToken);
		HttpUtils.addReponseHeader(response, tokenHeaderResponse, invalidToken);
	}
	

}
