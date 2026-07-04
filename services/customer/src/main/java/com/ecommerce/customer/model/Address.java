package com.ecommerce.customer.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Address {
    @NotBlank
    private String street;

    @NotBlank
    private String houseNumber;

    @NotBlank
    private String zipCode;
}
