package com.ecommerce.customer.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmailAlreadyExistsException extends RuntimeException {
    private final String msg;
}
