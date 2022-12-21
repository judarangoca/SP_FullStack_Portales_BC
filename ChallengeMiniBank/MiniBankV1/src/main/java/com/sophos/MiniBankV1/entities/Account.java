package com.sophos.MiniBankV1.entities;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int accountId ;
	
	private String accountNumber;
	private int customerId ; 
	private String accountType;
	private String accountStatus;
	private boolean exemptOfGmf;
	private LocalDateTime accountCreationDate = LocalDateTime.now();
	private float accountBalance;
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public LocalDateTime getAccountCreationDate() {
		return accountCreationDate;
	}
	public void setAccountCreationDate(LocalDateTime accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}
	public float getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(float f) {
		this.accountBalance = f;
	}
	public boolean isExemptOfGmf() {
		return exemptOfGmf;
	}
	public void setExemptOfGmf(boolean exemptOfGmf) {
		this.exemptOfGmf = exemptOfGmf;
	}

	

	

}
