package com.fantasy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.fantasy.*.mapper")
public class SpringBootShiroApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootShiroApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootShiroApplication.class);
	}


}
