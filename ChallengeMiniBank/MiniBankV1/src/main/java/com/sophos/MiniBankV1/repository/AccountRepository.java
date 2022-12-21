package com.sophos.MiniBankV1.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sophos.MiniBankV1.entities.Account;

import antlr.collections.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	 @Query(value="select * from accounts where (customer_Id = ?1);", nativeQuery = true)
     ArrayList<Account> findByCustomerID(String customerId);
	 
	 @Modifying
	 @Query(value="UPDATE accounts SET account_status = ?1 WHERE (account_id = ?2);", nativeQuery= true)
	 void ModifyStatusById(String newStatus, int accountId);

}
