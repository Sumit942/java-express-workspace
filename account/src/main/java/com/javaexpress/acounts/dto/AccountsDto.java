package com.javaexpress.acounts.dto;

import lombok.Data;

@Data
public class AccountsDto {
    private Long accountNumber;
    private Long customerId;
    private String accountType;
    private String branchAddress;
}
