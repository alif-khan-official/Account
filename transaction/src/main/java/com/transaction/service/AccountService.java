package com.transaction.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.model.Account;
import com.transaction.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public void addAccount(Account account)   
	{  
		accountRepository.save(account);  
	}
	
	public void updateAccount(Account account)   
	{  
		accountRepository.save(account);  
	}
	
	public List<Account> getAllAccounts()   
	{  
		List<Account> accounts = new ArrayList<Account>();  
		accountRepository.findAll().forEach(account->accounts.add(account));  
		return accounts;  
	}  
	
	public Account getAccountById(long id)   
	{  
		return accountRepository.findById(id).get();  
	} 
	
	public Account getAccountByUserId(long id)   
	{  
		return accountRepository.findById(id).get();  
	}
	
	public Account getAccountBalanceById(long id)   
	{  
		return accountRepository.findById(id).get();  
	} 
	
	public void transaction(Account account)   
	{  
		accountRepository.save(account);  
	} 
}
