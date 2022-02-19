package com.example.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ValidationConfig {

	@Bean
	public ValidatingMongoEventListener validationMongoEventListener() {
		System.out.println("HIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
		return new ValidatingMongoEventListener(validator());
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		System.out.print("HELLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLO");
		return new LocalValidatorFactoryBean();
		
	}
}
