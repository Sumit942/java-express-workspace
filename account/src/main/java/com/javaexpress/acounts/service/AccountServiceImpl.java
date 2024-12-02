package com.javaexpress.acounts.service;

import com.javaexpress.acounts.dto.AccountsDto;
import com.javaexpress.acounts.dto.CustomerDto;
import com.javaexpress.acounts.entity.Accounts;
import com.javaexpress.acounts.entity.Customer;
import com.javaexpress.acounts.exception.CustomerAlreadyExistsException;
import com.javaexpress.acounts.exception.ResourceNotFoundException;
import com.javaexpress.acounts.mapper.AccountsMapper;
import com.javaexpress.acounts.mapper.CustomerMapper;
import com.javaexpress.acounts.repository.AccountRepository;
import com.javaexpress.acounts.repository.CustomerRepository;
import com.javaexpress.acounts.service.impl.IAccountsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements IAccountsService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {
        // convert customerDto to Customer Entity
        var customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        // validate mobile no in db
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("customer already registered with given mobile number " + customerDto.getMobileNumber());
        }

        // save customer info in db
        var dbCustomer = customerRepository.save(customer);

        log.info("customer created Successfully!!");

        //create new account
        accountRepository.save(createNewAccount(dbCustomer));

        log.info("new account created successfully!");
    }

    private Accounts createNewAccount(Customer dbCustomer) {
        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(dbCustomer.getCustomerId());
        newAccounts.setAccountType("SAVING");
        newAccounts.setBranchAddress("Kalher, Bhiwandi");
        long randomAccountNo = 10000000L + new Random().nextInt(9000000);
        newAccounts.setAccountNumber(randomAccountNo);
        return newAccounts;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found by mobileNo: "+ mobileNumber));
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account not found by customerId: " + customer.getCustomerId()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountDto(accounts, new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto == null) {
            throw new ResourceNotFoundException("Update Operation failed.");
        }
        Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Account not found acNo: " + accountsDto.getAccountNumber()));
        AccountsMapper.mapToAccount(accountsDto, accounts);
        accounts = accountRepository.save(accounts);

        Long customerId = accounts.getCustomerId();
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("CustomerId Not Found"));
        CustomerMapper.mapToCustomer(customerDto, customer);
        customerRepository.save(customer);

        return true;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found by mobileNo: "+ mobileNumber));
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
