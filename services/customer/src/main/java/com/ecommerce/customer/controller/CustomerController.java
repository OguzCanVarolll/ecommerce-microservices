package com.ecommerce.customer.controller;

import com.ecommerce.customer.dto.CustomerRequest;
import com.ecommerce.customer.dto.CustomerResponse;
import com.ecommerce.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
        String customerId = service.createCustomer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CustomerCreatedResponse(customerId));
    }
    @PutMapping("/{customer-id}")
    public ResponseEntity<Void> updateCustomer( @PathVariable("customer-id") String customerId,
            @RequestBody @Valid CustomerRequest request){
        service.updateCustomer(request, customerId);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(service.findAllCustomers());
    }
    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existById(@PathVariable("customer-id") String customerId){
        return ResponseEntity.ok(service.existById(customerId));
    }
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId){
        return ResponseEntity.ok(service.findById(customerId));
    }
    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") String customerId){
        service.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
