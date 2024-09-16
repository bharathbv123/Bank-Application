package com.bank.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class AccountDto {

	private Long id;
	private String holder_name;
	private LocalDate registered_date;
	private double balance;
	
	
	
	public AccountDto() {
		super();
	}
	public AccountDto(Long id, String holder_name, LocalDate registered_date, double balance) {
		super();
		this.id = id;
		this.holder_name = holder_name;
		this.registered_date = registered_date;
		this.balance = balance;
	}
	public Long getId() {
		return id;
	}
	public String getHolder_name() {
		return holder_name;
	}
	public LocalDate getRegistered_date() {
		return registered_date;
	}
	public double getBalance() {
		return balance;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
	}
	public void setRegistered_date(LocalDate registered_date) {
		this.registered_date = registered_date;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}	
	
	
	
}
