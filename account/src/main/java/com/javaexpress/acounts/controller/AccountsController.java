package com.javaexpress.acounts.controller;

import com.javaexpress.acounts.dto.CustomerDto;
import com.javaexpress.acounts.service.impl.IAccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api")
@Slf4j
@RefreshScope
public class AccountsController {

    @Autowired
    private IAccountsService iAccountsService;

    @Value("${build.version}")
    private String buildVersion;

    @PostMapping("/create")
    public String createAccount(@RequestBody CustomerDto customerDto) {
        log.info("createAccount()");
        iAccountsService.createAccount(customerDto);
        return "Accounts Created Successfully!!";
    }

    @GetMapping("/fetch")
    public CustomerDto fetchAccountDetails(@RequestParam String mobileNumber) {
        return iAccountsService.fetchAccount(mobileNumber);
    }

    @PutMapping("/update")
    public Boolean updateAccountDetails(@RequestBody CustomerDto customerDto) {
        return iAccountsService.updateAccount(customerDto);
    }

    @DeleteMapping("/delete")
    public Boolean deleteAccountDetails(@RequestParam String mobileNumber) {
        return iAccountsService.deleteAccount(mobileNumber);
    }

    @GetMapping("build-info")
    public String getBuildVersion() {
        return buildVersion;
    }
}
