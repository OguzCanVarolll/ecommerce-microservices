package com.ecommerce.customer.service;

import com.ecommerce.customer.dto.CustomerRequest;
import com.ecommerce.customer.dto.CustomerResponse;
import com.ecommerce.customer.exception.CustomerNotFoundException;
import com.ecommerce.customer.exception.EmailAlreadyExistsException;
import com.ecommerce.customer.mapper.CustomerMapper;
import com.ecommerce.customer.model.Customer;
import com.ecommerce.customer.repository.CustomerRepository;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    //TOCTOU
    public String createCustomer(CustomerRequest request){
        if (repository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        try {
            var customer = repository.save(mapper.toCustomer(request));
            return customer.getId();
        } catch (DuplicateKeyException ex) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }

    public void updateCustomer(CustomerRequest request ,String customerId) {
        var customer = repository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException(
                        format("Cannot update customer:: No customer found with provided ID:: %s", customerId)
                ));
        if (request.email() != null &&
                (!request.email().equals(customer.getEmail())
                        && repository.existsByEmail(request.email()))) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        mergeCustomer(customer,request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        customer.setFirstname(request.firstname());
        customer.setLastname(request.lastname());
        customer.setEmail(request.email());
        customer.setAddress(request.address());
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .toList();
    }

    public boolean existById(String customerId) {
        return repository.existsById(customerId);
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException(format("No customer found with the provided ID:: %s", customerId)));

    }

    public void deleteCustomer(String customerId) {
        var customer = repository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException(
                        format("No customer found with the provided ID:: %s", customerId)));

        repository.delete(customer);
    }
}
