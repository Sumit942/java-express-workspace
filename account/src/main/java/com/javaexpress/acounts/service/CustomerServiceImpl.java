package com.javaexpress.acounts.service;


import com.javaexpress.acounts.clients.CartFeignClient;
import com.javaexpress.acounts.clients.LoansFeignClient;
import com.javaexpress.acounts.dto.AccountsDto;
import com.javaexpress.acounts.dto.CardsDto;
import com.javaexpress.acounts.dto.CustomerDetailsDto;
import com.javaexpress.acounts.entity.Accounts;
import com.javaexpress.acounts.entity.Customer;
import com.javaexpress.acounts.exception.ResourceNotFoundException;
import com.javaexpress.acounts.repository.AccountRepository;
import com.javaexpress.acounts.repository.CustomerRepository;
import com.javaexpress.acounts.service.impl.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CartFeignClient cartFeignClient;

    @Autowired
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found by mobileNo: "+ mobileNumber));
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account not found by customerId: " + customer.getCustomerId()));

        CardsDto cardsDto = cartFeignClient.fetchCardDetails(mobileNumber);
        AccountsDto.LoanDto loanDto = loansFeignClient.fetchLoan(mobileNumber);

        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
        BeanUtils.copyProperties(customer, customerDetailsDto);

        AccountsDto accountsDto = new AccountsDto();
        BeanUtils.copyProperties(accounts, accountsDto);
        customerDetailsDto.setAccountsDto(accountsDto);

        if (cardsDto != null) {
            customerDetailsDto.setCardsDto(cardsDto);
        }
        if (loanDto != null) {
            customerDetailsDto.setLoanDto(loanDto);
        }

        return customerDetailsDto;
    }
}
