package com.leno.bank.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value,Long senderI,Long receiverId) {



}
