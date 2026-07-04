package com.ecommerce.customer.repository;

import com.ecommerce.customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    boolean existsByEmail(String email);
    Optional<Customer> findByEmail(String email);
}
