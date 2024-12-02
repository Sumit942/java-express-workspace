package com.javaexpress.loans.controller;

import com.javaexpress.loans.dto.LoanDto;
import com.javaexpress.loans.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class LoansController {

    @Autowired
    private ILoanService iLoanService;

    @PostMapping("create")
    public String addLoan(@RequestParam String mobileNumber) {
        iLoanService.createLoan(mobileNumber);
        return "Loan added successfully!!";
    }

    @GetMapping("fetch")
    public LoanDto fetchLoan(@RequestParam String mobileNumber) {
        return iLoanService.fetchLoanDetails(mobileNumber);
    }

    @PutMapping("update")
    public Boolean updateLoan(@RequestBody LoanDto loansDto) {
        return iLoanService.updateLoanDetails(loansDto);
    }

    @DeleteMapping("delete")
    public Boolean deleteLoan(@RequestParam String mobileNumber) {
        return iLoanService.deleteLoanDetails(mobileNumber);
    }
}
