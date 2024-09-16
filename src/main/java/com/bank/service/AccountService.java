package com.bank.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.dto.AccountDto;
import com.bank.entities.Account;


public interface AccountService {
	
	public AccountDto createAccount(AccountDto accountDto);
	public AccountDto getAccountById(long id);
	
	AccountDto deposite(Long id, double amount);
	
	AccountDto withdrawn(Long id, double amount);
	List<AccountDto> getAllAccounts();
	void deleteAccount(Long id);
	List<AccountDto> getAllAccountsBetweenDateRange(LocalDate startDate, LocalDate endDate);
	List<AccountDto> getAllAccountsStartingWith(String prefix);
	

}
