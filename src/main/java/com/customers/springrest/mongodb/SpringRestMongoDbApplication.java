package com.customers.springrest.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpringRestMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestMongoDbApplication.class, args);
	}
}
