package com.transaction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.model.Ledger;
import com.transaction.repository.LedgerRepository;

@Service
public class LedgerService {

	@Autowired
	private LedgerRepository ledgerRepository;
	
	public Ledger getLedgerById(long id)   
	{  
		return ledgerRepository.findById(id).get();  
	} 
	
	public void saveDeposit(Ledger deposit)   
	{  
		ledgerRepository.save(deposit);  
	}
	
	public void saveWithdraw(Ledger withdraw)   
	{  
		ledgerRepository.save(withdraw);  
	}
	
	public void saveTransfer(Ledger transfer)   
	{  
		ledgerRepository.save(transfer);  
	}
	
	public List<Ledger> getLedgerByUserId(long userId)   
	{	
		List<Ledger> ledger = new ArrayList<Ledger>();  
		ledgerRepository.findAll().forEach(l->ledger.add(l));  
		return ledger;  
	} 
}
