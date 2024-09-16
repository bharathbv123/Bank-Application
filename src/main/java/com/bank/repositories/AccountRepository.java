package com.bank.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.entities.Account;
	
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	@Query("select c from Account c where c.registered_date between :startDate and :endDate")
	List<Account> findAccountsRegisteredBetween(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);

	@Query("select c from Account c where c.holder_name Like :prefix%")
	List<Account> findByHolder_NameStartingWith(@Param("prefix") String prefix);
}
