package com.bank.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String holder_name;
	private LocalDate registered_date;
	private double balance;
	public Account(Long id, String holder_name, LocalDate registered_date, double balance) {
		super();
		this.id = id;
		this.holder_name = holder_name;
		this.registered_date = registered_date;
		this.balance = balance;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHolder_name() {
		return holder_name;
	}
	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
	}
	public LocalDate getRegistered_date() {
		return registered_date;
	}
	public void setRegistered_date(LocalDate registered_date) {
		this.registered_date = registered_date;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

}
