package com.example.demo.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.server.UserService;

import java.io.IOException;

@Component
public class AuthFilter implements Filter {

	@Autowired
	private UserService userService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String path = httpRequest.getRequestURI();
		String method = httpRequest.getMethod();
		if (path.startsWith("/demo/static/") || path.startsWith("/demo/js/")) {
			chain.doFilter(request, response);
			return;
		}

		String username = httpRequest.getParameter("username");
		String password = httpRequest.getParameter("password");

		if (path.equals("/demo/login") && method.equals("POST")) {
			httpResponse.setContentType("application/json");
			httpResponse.setCharacterEncoding("UTF-8");

			if (username != null && password != null && userService.authenticate(username, password)) {
				httpRequest.getSession().setAttribute("user", username);
				httpResponse.getWriter().write("{\"success\": true}");
			} else {
				httpResponse.getWriter().write("{\"success\": false, \"message\": \"帳號密碼錯誤\"}");
			}
			return;
		}

		Object user = httpRequest.getSession().getAttribute("user");
		if (user == null && !path.equals("/demo/login")) {
			httpResponse.sendRedirect("/demo/login");
		} else {
			chain.doFilter(request, response);
		}
	}

}
