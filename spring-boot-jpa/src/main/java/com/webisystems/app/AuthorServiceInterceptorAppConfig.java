package com.webisystems.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.webisystems.app.interceptors.AuthorServiceInterceptor;

@Component
public class AuthorServiceInterceptorAppConfig implements WebMvcConfigurer {
	  @Autowired
	   AuthorServiceInterceptor authorServiceInterceptor;
	  
	  @Autowired
	  private LocaleChangeInterceptor localChangeInterceptor;

	   @Override
	   public void addInterceptors(InterceptorRegistry registry) {
		      registry.addInterceptor(authorServiceInterceptor);
		      registry.addInterceptor(localChangeInterceptor);
	   }
}
