package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@ComponentScan(basePackages = "com.spring.*")
@EnableJpaRepositories("com.spring.*")
@EntityScan("com.spring.*")
@OpenAPIDefinition(info = @Info(title = "Small Application"))
@SecurityScheme(
	    name = "bearerAuth",
	    type = SecuritySchemeType.HTTP,
	    bearerFormat = "JWT",
	    scheme = "Bearer"
	)
public class SpringbootProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProject1Application.class, args);
	}

}
