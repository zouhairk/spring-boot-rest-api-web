package com.webisystems.app;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

 @SpringBootApplication
public class SpringBootJpaApplication {
	
	private static final Logger LOG = LoggerFactory.getLogger(SpringBootJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}
	
	 @Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	 
	 @Bean
	 public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	       @Override
	       public void addCorsMappings(CorsRegistry registry) {
	          registry.addMapping("/author").allowedOrigins("http://localhost:9000");
	       }    
	    };
	 }
	 
	 
	 @Bean
	 public LocaleResolver localeResolver() {
	    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
	    sessionLocaleResolver.setDefaultLocale(Locale.US);
	    return sessionLocaleResolver;
	 }
	 
	 
	 @Bean
	 public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	    localeChangeInterceptor.setParamName("language");
 	    return localeChangeInterceptor;
	 }

}
