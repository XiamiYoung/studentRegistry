package edu.dufe.student.registry.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.dufe.student.registry.constants.Constants;
import edu.dufe.student.registry.data.error.ErrorData;
import edu.dufe.student.registry.data.error.ErrorResData;
import edu.dufe.student.registry.data.in.UserData;
import edu.dufe.student.registry.exception.RestfulException;
import edu.dufe.student.registry.service.UserService;
import edu.dufe.student.registry.utils.ExceptionUtils;
import edu.dufe.student.registry.utils.HttpUtils;
import edu.dufe.student.registry.utils.JsonUtils;
import edu.dufe.student.registry.utils.JwtTokenUtil;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	@Value("${jwt.header.token.name}")
	private String headerName;
	@Value("${jwt.header.user.name}")
	private String headerUserName;
	@Value("${jwt.header.token.header.name}")
	private String tokenHeaderResponse;
	@Value("${jwt.authz.type}")
	private String authzType;

	@Resource
	private UserService userService;
	@Resource
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			// read header values
			String authHeader = request.getHeader(headerName);
			String loggedInUserHeader = request.getHeader(headerUserName);
			String uri = request.getRequestURI();

			if (authHeader != null && authHeader.startsWith(authzType)&&!uri.endsWith("logout")) {
				String authToken = authHeader.substring(authzType.length());
				String tokenUsername = jwtTokenUtil.getUsernameFromToken(authToken);
				if (tokenUsername != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserData userData = (UserData) userService.loadUserByUsername(tokenUsername);
					if (jwtTokenUtil.validateToken(authToken, loggedInUserHeader)) {
						// create security token
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								userData, null, userData.getAuthorities());
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authentication);

						String refreshedToken = jwtTokenUtil.refreshToken(authToken);
						// add token and aid to header
						HttpUtils.addReponseHeader(response, tokenHeaderResponse, refreshedToken);
						HttpUtils.addReponseHeader(response, headerUserName, tokenUsername);
					} else {
						// jwtTokenUtil.del(authToken);
					}
				}
			}
		} catch (RestfulException be) {
			ErrorData errorData = be.getErrorData();
			ErrorResData errorResData = ExceptionUtils.getErrorResData(errorData);
			response.setStatus(be.getErrorData().getHttpCode());
			response.getWriter().write(JsonUtils.convertObjectToJson(errorResData));
			response.setContentType(Constants.CONTENT_TYPE_JSON);
			return;
		}

		filterChain.doFilter(request, response);
	}
}
