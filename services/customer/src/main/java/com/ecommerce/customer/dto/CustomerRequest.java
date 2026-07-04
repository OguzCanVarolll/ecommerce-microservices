package com.ecommerce.customer.dto;

import com.ecommerce.customer.model.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest (

         @NotBlank(message = "Customer firstname is required")
         String firstname,

         @NotBlank(message = "Customer lastname is required")
         String lastname,

         @NotBlank(message = "Customer email is required")
         @Email(message = "Customer email is not a valid email address")
         String email,

         @Valid
         Address address
) {}
