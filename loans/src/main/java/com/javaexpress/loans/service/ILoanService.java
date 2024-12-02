package com.javaexpress.loans.service;

import com.javaexpress.loans.dto.LoanDto;

public interface ILoanService {

    void createLoan(String mobileNumber);

    LoanDto fetchLoanDetails(String mobileNumber);

    Boolean updateLoanDetails(LoanDto loansDto);

    Boolean deleteLoanDetails(String mobileNumber);
}
