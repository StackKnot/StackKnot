package com.codeup.stackknot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.CacheControl;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class StackKnotApplication {

//	@Configuration
//	public class WebConfiguration extends WebMvcConfigurationSupport {
//
//		@Override
//		public void addResourceHandlers(ResourceHandlerRegistry registry) {
//			registry.addResourceHandler("/**")
//					.addResourceLocations("classpath:/static/");
//		}
//	}

	public static void main(String[] args) {
		SpringApplication.run(StackKnotApplication.class, args);
	}

}
