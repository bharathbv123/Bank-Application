package com.bank.mapper;

import java.time.LocalDate;

import com.bank.dto.AccountDto;
import com.bank.entities.Account;

public class AccountMapper {
	
	public static Account maptoAccount(AccountDto accountDto) {
		
		Account account=new Account(
				accountDto.getId(),
				accountDto.getHolder_name(),
				accountDto.getRegistered_date(),
				accountDto.getBalance());
		return account;
	}
	
	public static AccountDto maptoAccountDto(Account account) {
		AccountDto accountDto=new AccountDto(
				account.getId(), 
				account.getHolder_name(), 
				account.getRegistered_date(), 
				account.getBalance());
		return accountDto;
	}

}
