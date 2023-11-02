package com.leno.bank.dtos;

import com.leno.bank.domain.user.Type;

import java.math.BigDecimal;

public record UserDTO(
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String email,
        String password,
        Type type) {
}
