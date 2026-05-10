package com.devamatre.j2eepatterns.cachingfilter.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A filter that caches pages after they are generated.
 */
public class CacheFilter implements Filter {
	/** the filter configuration */
	private FilterConfig filterConfig = null;
	
	/** the cached data */
	private HashMap cache;
	
	/**
	 * Constructor
	 */
	public CacheFilter() {
	}
	
	/**
	 * Perform the actual caching. If a given key is available in the
	 * cache, return it immediately. If not, cache the result using a
	 * CacheResponseWrapper
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// the cache key is the URI + query string
		String qs = req.getQueryString();
		String key = qs != null ? req.getRequestURI() + "?" + qs : req.getRequestURI();
		
		// only cache GET requests that contain cacheable data
		if (req.getMethod().equalsIgnoreCase("get") && isCacheable(key)) {
			// try to retrieve the data from the cache
			byte[] data = (byte[]) cache.get(key);
			
			// on a cache miss, generate the result normally and add it to the
			// cache
			if (data == null) {
				CacheResponseWrapper crw = new CacheResponseWrapper(res);
				chain.doFilter(request, crw);
				data = crw.getBytes();
				if (data != null) {
					cache.put(key, data);
				}
			}
			
			// if the data was found, use it to generate the result
			if (data != null) {
				res.setContentType("text/html");
				res.setContentLength(data.length);
				
				try {
					OutputStream os = res.getOutputStream();
					os.write(data);
					os.flush();
					os.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} else {
			// generate the data normally if it was not cacheable
			chain.doFilter(request, response);
		}
	}
	
	/**
	 * Return whether the given URI + query string is cacheable. A real
	 * implementation should use some kind of policy here.
	 */
	private boolean isCacheable(String key) {
		return true;
	}
	
	/**
	 * Initialize the cache
	 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		
		cache = new HashMap();
	}
	
	/**
	 * Destroy the cache
	 */
	public void destroy() {
		cache.clear();
		
		cache = null;
		filterConfig = null;
	}
}
