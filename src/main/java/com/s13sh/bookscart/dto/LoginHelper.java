package com.s13sh.bookscart.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Component
public class LoginHelper {
    @NotEmpty(message = "* this is required field")
    private String username;
    @NotEmpty(message = "* this is required field")
    private String password;
}
