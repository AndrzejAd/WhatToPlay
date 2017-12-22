package whattoplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "whattoplay.controllers", "whattoplay.persistence", "whattoplay.services", "whattoplay.services.domain", "whattoplay.config", "whattoplay.config"})
@EntityScan( basePackages = {"whattoplay.domain"})
@EnableJpaRepositories("whattoplay.persistence")
public class WhattoplayApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WhattoplayApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WhattoplayApplication.class, args);
	}


}
