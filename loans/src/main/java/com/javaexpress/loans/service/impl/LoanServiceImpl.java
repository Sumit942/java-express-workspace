package com.javaexpress.loans.service.impl;

import com.javaexpress.loans.dto.LoanDto;
import com.javaexpress.loans.entity.Loan;
import com.javaexpress.loans.exception.LoanAlreadyExistException;
import com.javaexpress.loans.repository.LoanRepository;
import com.javaexpress.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {
    private LoanRepository loanRepository;
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoans = loanRepository.findByMobileNumber(mobileNumber);
        if (optionalLoans.isPresent()) {
            throw new LoanAlreadyExistException("Loan already exist with mobileNumber: " + mobileNumber);
        }
        createNewLoan(mobileNumber);
    }

    private void createNewLoan(String mobileNumber) {
        Loan loan = new Loan();
        long randomLoanNo = 1000000L + new Random().nextInt(1000000);
        loan.setLoanNumber(String.valueOf(randomLoanNo));
        loan.setMobileNumber(mobileNumber);
        loan.setLoanType("PERSONAL_LOAN");
        loan.setAmountPaid(0);
        loan.setTotalLoan(0);
        loan.setOutstandingAmount(0);
        loanRepository.save(loan);
    }

    @Override
    public LoanDto fetchLoanDetails(String mobileNumber) {
        Loan dbLoanDetails = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new LoanAlreadyExistException("Loan account doesn't exist for mobileNo: " + mobileNumber));
        LoanDto loanDto = new LoanDto();
        BeanUtils.copyProperties(dbLoanDetails, loanDto);
        return loanDto;
    }

    @Override
    public Boolean updateLoanDetails(LoanDto loansDto) {
        Loan dbLoanDetails = loanRepository.findByMobileNumber(loansDto.getMobileNumber())
                .orElseThrow(() -> new LoanAlreadyExistException("Loan account doesn't exist for mobileNo: " + loansDto.getMobileNumber()));
        BeanUtils.copyProperties(loansDto, dbLoanDetails);
        loanRepository.save(dbLoanDetails);
        return true;
    }

    @Override
    public Boolean deleteLoanDetails(String mobileNumber) {
        Loan dbLoanDetails = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new LoanAlreadyExistException("Loan account is not associated with mobileNo: " + mobileNumber));
        loanRepository.deleteById(dbLoanDetails.getLoanId());
        return true;
    }
}
