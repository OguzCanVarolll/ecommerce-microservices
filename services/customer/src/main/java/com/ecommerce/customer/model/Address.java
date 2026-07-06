package com.ecommerce.customer.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

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
    @Pattern(regexp = "\\d{5}", message = "zipCode must be 5 digits")
    private String zipCode;
}
