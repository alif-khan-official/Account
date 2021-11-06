package com.transaction.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.model.Account;
import com.transaction.model.DeactivateAccountInputModel;
import com.transaction.model.Deposit;
import com.transaction.model.Ledger;
import com.transaction.model.Transfer;
import com.transaction.model.UpdateAccountInputModel;
import com.transaction.model.Withdraw;
import com.transaction.service.AccountService;
import com.transaction.service.LedgerService;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private LedgerService ledgerService;
	
	@PostMapping("/account")  
	private long addAccount(@RequestBody Account account)   
	// private boolean addAccount(@RequestBody Account account) 
	{  
		accountService.addAccount(account);  
		return account.getId();
		// return true;
	}  
	
	@PutMapping("/account")  
	private boolean updateAccount(@RequestBody UpdateAccountInputModel inputModel)
	{  
		Account a = accountService.getAccountById(inputModel.getId());
		
		a.setAccountName(inputModel.getAccountName());
		
		accountService.updateAccount(a);  
		return true;  
	}
	
	@PutMapping("/account/deactive")  
	private boolean deactivateAccount(@RequestBody DeactivateAccountInputModel inputModel)
	{  
		Account a = accountService.getAccountById(inputModel.getId());
		
		a.setStatus(false);
		
		accountService.updateAccount(a);  
		return true;  
	}
	
	@GetMapping("/accounts")  
	private List<Account> getAllAccounts()   
	{  
		return accountService.getAllAccounts();  
	} 
	
	@GetMapping("/account/{id}") 
	private Account getAccountById(@PathVariable("id") long id)   
	{  
		return accountService.getAccountById(id);  
	} 
	
	@GetMapping("/balance/{id}") 
	private Account getAccountBalanceById(@PathVariable("id") long id)   
	{  
		return accountService.getAccountBalanceById(id);  
	}
	
	@PostMapping("/deposit")  
	// private float deposit(@RequestBody Deposit inputModel)
	private boolean deposit(@RequestBody Deposit inputModel)
	{  
		Account a = accountService.getAccountByUserId(inputModel.getUserId());
		
		a.setBalance(a.getBalance() + inputModel.getAmount());
		
		accountService.transaction(a);
		
		Ledger d = new Ledger();
		
		d.setUserId(inputModel.getUserId());
		d.setAmount(inputModel.getAmount());
		d.setTransactionType("Deposit");
		d.setDate(LocalDate.now());
		
		ledgerService.saveDeposit(d);
		
		// return a.getBalance();
		return true;
	}
	
	@PostMapping("/withdraw")  
	// private float withdraw(@RequestBody Withdraw inputModel)
	private boolean withdraw(@RequestBody Withdraw inputModel)
	{  
		Account a = accountService.getAccountByUserId(inputModel.getUserId());
		
		a.setBalance(a.getBalance() - inputModel.getAmount());
		
		accountService.transaction(a);
		
		Ledger w = new Ledger();
		
		w.setUserId(inputModel.getUserId());
		w.setAmount(inputModel.getAmount());
		w.setTransactionType("Withdraw");
		w.setDate(LocalDate.now());
		
		ledgerService.saveWithdraw(w);
		
		// return a.getBalance();
		return true;
	}
	
	@PostMapping("/transfer")  
	// private float transfer(@RequestBody Transfer inputModel)
	private boolean transfer(@RequestBody Transfer inputModel)
	{  
		Account from = accountService.getAccountByUserId(inputModel.getUserId());
		Account to = accountService.getAccountByUserId(inputModel.getTransferToUserId());
		
		from.setBalance(from.getBalance() - inputModel.getAmount());
		to.setBalance(to.getBalance() + inputModel.getAmount());
		
		accountService.transaction(from);
		accountService.transaction(to);
		
		Ledger t = new Ledger();
		
		t.setUserId(inputModel.getUserId());
		t.setAmount(inputModel.getAmount());
		t.setTransactionType("Transfer");
		t.setDate(LocalDate.now());
		
		ledgerService.saveTransfer(t);
		
		// return from.getBalance();
		return true;
	}
	
}
