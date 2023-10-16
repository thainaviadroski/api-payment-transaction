package com.pickpaysimplify.dto;

import com.pickpaysimplify.enums.UserType;

import java.math.BigDecimal;

public record UserDTO(String name, String document, String email, String password, UserType type, BigDecimal balence) {
}
