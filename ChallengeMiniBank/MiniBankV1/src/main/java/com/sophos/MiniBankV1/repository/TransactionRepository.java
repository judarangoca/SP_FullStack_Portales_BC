package com.sophos.MiniBankV1.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sophos.MiniBankV1.entities.Account;
import com.sophos.MiniBankV1.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
		
	@Query(value="select * from transactions where (origin_Account_Id = ?1 OR destination_Account_Id=?1);", nativeQuery = true)
	@Modifying
    Optional<List<Transaction>> getAllByAccountId(String string);


}
