package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

import entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

}
