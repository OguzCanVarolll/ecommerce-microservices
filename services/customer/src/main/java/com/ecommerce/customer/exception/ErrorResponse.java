package com.ecommerce.customer.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
)
{}
