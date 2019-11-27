package com.customers.springrest.mongodb.controller;

import com.customers.springrest.mongodb.exception.CustomerNotFoundException;
import com.customers.springrest.mongodb.model.Customer;
import com.customers.springrest.mongodb.payload.ErrorResponse;
import com.customers.springrest.mongodb.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import javax.validation.Valid;

/**
 * Created by Jose Corominas on 11/27/19.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @GetMapping("/customers")
    public Flux<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @PostMapping("/customers/create")
    public Mono<ResponseEntity<Customer>> postCustomer(@Valid @RequestBody Customer customer) {
        return repository.save(customer).map(newCustomer -> new ResponseEntity<>(newCustomer, HttpStatus.CREATED));
    }

    @GetMapping("/customers/{id}")
    public Mono<ResponseEntity<Customer>> getCustomerById(@PathVariable(value = "id") String customerId) {
        return repository.findById(customerId)
                .map(savedCustomer -> ResponseEntity.ok(savedCustomer))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("customers/age/{age}")
	public Flux<Customer> findByAge(@PathVariable int age) {

		return repository.findByAge(age);
	}

    @PutMapping("/customers/{id}")
    public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable(value = "id") String customerId,
                                                   @Valid @RequestBody Customer customer) {
        return repository.findById(customerId)
                .flatMap(existingCustomer -> {
                    existingCustomer.setName(customer.getName());
                    existingCustomer.setActive(customer.isActive());
                    existingCustomer.setAge(customer.getAge());
                    return repository.save(existingCustomer);
                })
                .map(updateCustomer -> new ResponseEntity<>(updateCustomer, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/customers/{id}")
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable(value = "id") String customerId) {

        return repository.findById(customerId)
                .flatMap(existingCustomer ->
                        repository.delete(existingCustomer)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @DeleteMapping("/customers/delete")
	public Mono<ResponseEntity<Void>> deleteAllCustomers() {
		System.out.println("Delete All Customers...");

		return repository.deleteAll().then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
	}

    // Customers are Sent to the client as Server Sent Events
    @GetMapping(value = "/stream/customers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> streamAllCustomers() {
        return repository.findAll();
    }




    /*
        Exception Handling Examples (These can be put into a @ControllerAdvice to handle exceptions globally)
    */

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A Customer with the same id already exists"));
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity handleTweetNotFoundException(CustomerNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

}
