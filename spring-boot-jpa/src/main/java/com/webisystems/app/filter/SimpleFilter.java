package com.webisystems.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

@Component
public class SimpleFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		
	}
	
	
	 @Override
	   public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) 
	      throws IOException, ServletException {
	      
	      System.out.println("Server name:"+request.getServerName());
	      System.out.println("Remote Host:"+request.getRemoteHost());
	      System.out.println("Remote Address:"+request.getRemoteAddr());
	      filterchain.doFilter(request, response);
	   }
	 
	 @Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	

	
	

}
