package com.sophos.MiniBankV1.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transactionId;
	private LocalDateTime transactionDate;
	private float transactionValue;
	private String typeOfTransaction;
	private int originAccountId ;
	private int destinationAccountId ;
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	public float getTransactionValue() {
		return transactionValue;
	}
	public void setTransactionValue(float transactionValue) {
		this.transactionValue = transactionValue;
	}
	
	public int getOriginAccountId() {
		return originAccountId;
	}
	public void setOriginAccountId(int originAccountId) {
		this.originAccountId = originAccountId;
	}
	public int getDestinationAccountId() {
		return destinationAccountId;
	}
	public void setDestinationAccountId(int destinationAccountId) {
		this.destinationAccountId = destinationAccountId;
	}

	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}
	public void setTypeOfTransaction(String typeOfTransaction) {
		this.typeOfTransaction = typeOfTransaction;
	}
	
	
	
	
}
