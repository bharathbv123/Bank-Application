package com.bank.service;

//import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bank.dto.AccountDto;
import com.bank.entities.Account;
import com.bank.mapper.AccountMapper;
import com.bank.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account=AccountMapper.maptoAccount(accountDto);
		Account savedAccount=accountRepository.save(account);
		return  AccountMapper.maptoAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(long id) {
		
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
		return AccountMapper.maptoAccountDto(account);
		
	}

	@Override
	public AccountDto deposite(Long id, double amount) {

		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
		double total=account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.maptoAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdrawn(Long id, double amount) {
		
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doest not exist"));
		if(amount<=account.getBalance()) {
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.maptoAccountDto(savedAccount);
		}else {
			throw new RuntimeException("Insufficient Balance. Available Balance: "+account.getBalance());
		}
		
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		
		List<Account> accounts=accountRepository.findAll();
		return accounts.stream().map((account)->AccountMapper.maptoAccountDto(account)).collect(Collectors.toList());
		
		
	}

	@Override
	public void deleteAccount(Long id) {
		
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doest not exist"));
		accountRepository.deleteById(account.getId());
	}

	@Override
	public List<AccountDto> getAllAccountsBetweenDateRange(LocalDate startDate, LocalDate endDate) {
		
		List<Account> savedaccount=accountRepository.findAccountsRegisteredBetween(startDate, endDate);
		return savedaccount.stream().map((account)->AccountMapper.maptoAccountDto(account)).collect(Collectors.toList());
	}

	@Override
	public List<AccountDto> getAllAccountsStartingWith(String prefix) {
		
		List<Account> accounts=accountRepository.findByHolder_NameStartingWith(prefix);
		if(accounts.size()>0) {
		return accounts.stream().map((account)->AccountMapper.maptoAccountDto(account)).collect(Collectors.toList());
		}else {
			throw new RuntimeException("Account Does not exist");
		}
	}
	
	public Page<Account> getAllAccountPaging(int page, int size){
		
		Pageable pageable=PageRequest.of(page, size);
		Page<Account> accountPage =accountRepository.findAll(pageable);
//		return accountPage.stream().map((account)->AccountMapper.maptoAccountDto(account)).collect(Collectors.toList());
//		return accountRepository.findAll(pageable);
//		return (Page<AccountDto>) accountPage.stream()
//                .map(AccountMapper::maptoAccountDto)
//                .collect(Collectors.toList());
		return accountRepository.findAll(pageable);
	}

}
