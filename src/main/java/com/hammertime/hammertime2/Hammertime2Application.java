package com.hammertime.hammertime2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan({"com"})
@EntityScan({"com"})
@EnableJpaRepositories({"com"})
public class Hammertime2Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Hammertime2Application.class, args);
	}
}