package edu.dufe.student.registry.utils;

import javax.servlet.http.HttpServletResponse;

public class HttpUtils {
	public static void addReponseHeader(HttpServletResponse response,String name, String value) {
    	response.setHeader(name, value);
    }
}
