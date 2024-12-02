package com.javaexpress.acounts.dto;

import com.javaexpress.acounts.clients.LoanDto;
import lombok.Data;

@Data
public class CustomerDetailsDto {
    private String name;
    private String email;
    private String mobileNumber;
    private AccountsDto accountsDto;
    private CardsDto cardsDto;
    private LoanDto loanDto;
}
