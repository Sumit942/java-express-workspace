package com.javaexpress.acounts.service.impl;

import com.javaexpress.acounts.dto.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto mobileNumber);

    boolean deleteAccount(String mobileNumber);

}
