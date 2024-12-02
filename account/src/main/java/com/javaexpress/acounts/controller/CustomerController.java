package com.javaexpress.acounts.controller;

import com.javaexpress.acounts.dto.CustomerDetailsDto;
import com.javaexpress.acounts.service.impl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("fetchCustomerDetails")
    public CustomerDetailsDto fetchCustomerDetails(@RequestParam String mobileNumber) {
        return iCustomerService.fetchCustomerDetails(mobileNumber);
    }
}
