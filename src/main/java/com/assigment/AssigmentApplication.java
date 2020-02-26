package com.assigment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.assigment.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class AssigmentApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AssigmentApplication.class, args);
	}

	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(AssigmentApplication.class);
	    }
}
