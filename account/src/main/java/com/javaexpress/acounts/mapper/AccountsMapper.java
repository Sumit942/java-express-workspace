package com.javaexpress.acounts.mapper;

import com.javaexpress.acounts.dto.AccountsDto;
import com.javaexpress.acounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccountDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        accountsDto.setCustomerId(accounts.getCustomerId());
        return accountsDto;
    }

    public static Accounts mapToAccount(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        accounts.setCustomerId(accountsDto.getCustomerId());
        return accounts;
    }
}
