package com.example.springboot_crud_wk1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EnableJpaRepositories(basePackages = "com.example.repository")
@EntityScan(basePackages = "com.example.model")
public class SpringbootCrudWk1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudWk1Application.class, args);
	}

}
