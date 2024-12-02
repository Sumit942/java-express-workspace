package com.javaexpress.acounts.clients;

import com.javaexpress.acounts.dto.AccountsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("LOAN")
public interface LoansFeignClient {

    @GetMapping("api/fetch")
    public AccountsDto.LoanDto fetchLoan(@RequestParam String mobileNumber);
}
