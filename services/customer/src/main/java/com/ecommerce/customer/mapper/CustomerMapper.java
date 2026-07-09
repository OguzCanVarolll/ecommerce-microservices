package com.ecommerce.customer.mapper;

import com.ecommerce.customer.dto.AddressResponse;
import com.ecommerce.customer.dto.CustomerRequest;
import com.ecommerce.customer.dto.CustomerResponse;
import com.ecommerce.customer.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if(request == null){
            return null;
        }
        return Customer.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email().toLowerCase(Locale.ROOT))
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                new AddressResponse(
                        customer.getAddress().getStreet(),
                        customer.getAddress().getHouseNumber(),
                        customer.getAddress().getZipCode()
                )
        );
    }
}
