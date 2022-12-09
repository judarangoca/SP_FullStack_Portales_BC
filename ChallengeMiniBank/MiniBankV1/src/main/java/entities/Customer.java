package entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ; 
	private String identification_Type;
	private String identification_Number;
	private String first_Name;
	private String last_Name;
	private String email;
	private Date birth_Date;
	private int phone_Number;
	private Date creation_Date;
	
	//Generamos constructor por defecto
	public Customer() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentification_Type() {
		return identification_Type;
	}

	public void setIdentification_Type(String identification_Type) {
		this.identification_Type = identification_Type;
	}

	public String getIdentification_Number() {
		return identification_Number;
	}

	public void setIdentification_Number(String identification_Number) {
		this.identification_Number = identification_Number;
	}

	public String getFirst_Name() {
		return first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth_Date() {
		return birth_Date;
	}

	public void setBirth_Date(Date birth_Date) {
		this.birth_Date = birth_Date;
	}

	public int getPhone_Number() {
		return phone_Number;
	}

	public void setPhone_Number(int phone_Number) {
		this.phone_Number = phone_Number;
	}

	public Date getCreation_Date() {
		return creation_Date;
	}

	public void setCreation_Date(Date creation_Date) {
		this.creation_Date = creation_Date;
	}
	
	//Generamos Metodos Get y Set
	

	
}

