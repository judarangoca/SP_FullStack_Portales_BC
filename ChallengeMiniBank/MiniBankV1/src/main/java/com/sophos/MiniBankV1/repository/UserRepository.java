package com.sophos.MiniBankV1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sophos.MiniBankV1.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
