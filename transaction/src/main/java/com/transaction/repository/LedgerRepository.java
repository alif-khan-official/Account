package com.transaction.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.transaction.model.Ledger;

public interface LedgerRepository extends CrudRepository<Ledger, Long> {
	
	public List<Ledger> getByUserId(long userId);

}
