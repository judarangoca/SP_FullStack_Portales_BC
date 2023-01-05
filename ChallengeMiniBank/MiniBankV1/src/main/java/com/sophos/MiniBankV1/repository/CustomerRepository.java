package com.sophos.MiniBankV1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophos.MiniBankV1.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{	
}
