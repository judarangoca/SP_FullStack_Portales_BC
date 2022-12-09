package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	//Utilizaremos los metodos hereados de la dependencia JPA
	
}
