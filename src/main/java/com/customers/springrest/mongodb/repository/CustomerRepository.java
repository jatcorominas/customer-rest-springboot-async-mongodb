package com.customers.springrest.mongodb.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


import com.customers.springrest.mongodb.model.Customer;

import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String>{
	Flux<Customer> findByAge(int age);
}
