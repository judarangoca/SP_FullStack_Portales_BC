package entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY) //Nop se puede usar esta estrategia
	private int accountId ; 
	private String accountType;
	private String accountStatus;
	private Date accountCreationDate;
	

}
