package org.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("org.com")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public InternalResourceViewResolver getViewResolver() 
	{
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10240000);
		return multipartResolver;
	}

}
