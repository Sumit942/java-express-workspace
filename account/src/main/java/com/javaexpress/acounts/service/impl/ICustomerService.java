package com.javaexpress.acounts.service.impl;

import com.javaexpress.acounts.dto.CustomerDetailsDto;

public interface ICustomerService {


    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
