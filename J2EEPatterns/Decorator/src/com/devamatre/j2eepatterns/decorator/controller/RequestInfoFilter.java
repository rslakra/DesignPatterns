package com.devamatre.j2eepatterns.decorator.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * A simple filter to print out information about requests that we receive.
 */
public class RequestInfoFilter implements Filter {
	// describes the filter configuration
	private FilterConfig filterConfig = null;
	
	/**
	 * Constructor
	 */
	public RequestInfoFilter() {
	}
	
	/**
	 * Called when the filter is initialized
	 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
	
	/**
	 * Perform the actual filtering. Log request parameters and attributes
	 * to the ServletContext log
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ServletContext sc = filterConfig.getServletContext();
		
		// preprocess the request
		HttpServletRequest hrs = (HttpServletRequest) request;
		sc.log("Request Attributes:");
		sc.log("Method: " + hrs.getMethod());
		sc.log("QueryString: " + hrs.getQueryString());
		sc.log("Context: " + hrs.getContextPath());
		sc.log("RequestURL: " + hrs.getRequestURL());
		sc.log("RequestURI: " + hrs.getRequestURI());
		sc.log("PathInfo: " + hrs.getPathInfo());
		sc.log("ServletPath: " + hrs.getServletPath());
		
		// enumerate all parameters
		sc.log("Parameters:");
		for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
			String name = (String) e.nextElement();
			String vals[] = request.getParameterValues(name);
			
			StringBuffer out = new StringBuffer();
			out.append(name + "=");
			
			// each parameter may have multiple values
			for (int i = 0; i < vals.length; i++) {
				out.append(vals[i]);
			}
			
			// remove the trailing comma
			sc.log(out.substring(0, out.length() - 1));
		}
		
		// invoke the next filter in the chain
		chain.doFilter(request, response);
	}
	
	/**
	 * Called when the filter is deactivated
	 */
	public void destroy() {
	}
}
