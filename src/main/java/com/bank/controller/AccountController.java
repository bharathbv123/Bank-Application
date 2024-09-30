package com.bank.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

//import javax.swing.text.DateFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.AccountDto;
import com.bank.entities.Account;
import com.bank.service.AccountServiceImpl;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	
	@PostMapping
	public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountServiceImpl.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccount(@PathVariable long id){
		return new ResponseEntity<AccountDto>(accountServiceImpl.getAccountById(id),HttpStatus.OK);
	}
	
	@PutMapping("/{id}/deposite")
	public ResponseEntity<AccountDto> deposite(@PathVariable Long id,@RequestBody Map<String, Double> request ){
		Double amount=request.get("amount");
		return new ResponseEntity<AccountDto>(accountServiceImpl.deposite(id, amount),HttpStatus.OK);
	}
	
	@PutMapping("/{id}/withdrawn")
	public ResponseEntity<AccountDto> withdrawn(@PathVariable Long id, @RequestBody Map<String, Double> request){
		Double amount=request.get("amount");
		return new ResponseEntity<AccountDto>(accountServiceImpl.withdrawn(id, amount),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity< List<AccountDto>> getAllAccounts(){
		return new ResponseEntity<>(accountServiceImpl.getAllAccounts(),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteByAccount(@PathVariable Long id){
		accountServiceImpl.deleteAccount(id);
		return new ResponseEntity<>("Account is Deleted Successfully",HttpStatus.OK);
	}
	
	@GetMapping("/DateRange")
	public ResponseEntity<List<AccountDto>> getAllAcountsDateRange(@RequestParam ("start") String start, @RequestParam ("end") String end){

		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-DD");
		LocalDate startDate=LocalDate.parse(start, formatter);
		LocalDate endDate=LocalDate.parse(end, formatter);
		return new ResponseEntity<>( accountServiceImpl.getAllAccountsBetweenDateRange(startDate, endDate),HttpStatus.OK);
		//http://localhost:8080/accounts/DateRange?start=2019-01-01&end=2023-01-01
	}
	
	@GetMapping("/startswith")
	public ResponseEntity<List<AccountDto>> getAllAccountsStartsWith(@RequestParam String prefix){
		return new ResponseEntity<List<AccountDto>>(accountServiceImpl.getAllAccountsStartingWith(prefix),HttpStatus.OK);
//		http://localhost:8080/accounts/startswith?prefix=Bh
	}
	
	@GetMapping("/Paging")
	public Page<Account> getAccounts(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
return accountServiceImpl.getAllAccountPaging(page, size);
}
	
}
