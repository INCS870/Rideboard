package com.rideboard.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SecurityFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(SecurityFilter.class);
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.addHeader("X-Content-Type-Options", "nosniff");
		response.addHeader("X-Frame-Options", "SAMEORIGIN");
		//response.addHeader("Content-Security-Policy", "default-src 'self' 'unsafe-inline' 'unsafe-eval'");
		response.addHeader("Content-Security-Policy", "default-src 'self' www.amcharts.com  'unsafe-inline' 'unsafe-eval'");
		
		response.setHeader("X-Powered-By","");
		response.addHeader("X-XSS-Protection","1; mode=block"); 
		response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate, private");
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
		
		String urlStr = request.getRequestURI();
		if ((urlStr != null && urlStr.length() > 0 && "~"
				.equalsIgnoreCase(urlStr.substring(urlStr.length() - 1)))
				|| urlStr.indexOf("~?") > 0) {
			logger.info("Invalid URL.");

			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
