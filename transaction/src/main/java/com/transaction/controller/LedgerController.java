package com.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.model.Ledger;
import com.transaction.service.LedgerService;

@RestController
@RequestMapping("/api")
public class LedgerController {

	@Autowired
	private LedgerService ledgerService;
	
	/*
	@GetMapping("/ledger/{id}") 
	private Ledger getLedgerById(@PathVariable("id") long id)   
	{  
		return ledgerService.getLedgerById(id);  
	} 
	*/
	
	@GetMapping("/ledger/{userId}") 
	private List<Ledger> getLedgerByUserId(@PathVariable("userId") long userId)   
	{  
		return ledgerService.getLedgerByUserId(userId);  
	} 
	
}
