package com.pickpaysimplify.dto;

import java.math.BigDecimal;

public record TransactionDTO(Long sender, Long reciver, BigDecimal value) {
}
