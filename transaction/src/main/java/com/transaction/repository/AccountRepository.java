package com.transaction.repository;

import org.springframework.data.repository.CrudRepository;

import com.transaction.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> { 
	
}
