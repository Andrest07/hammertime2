package com.hammertime.hammertime2.domain.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hammertime.hammertime2.exceptions.TransactionNotFoundException;
import com.hammertime.hammertime2.service.IBackendService;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class TransactionController {
	
	@Autowired
	IBackendService backendService;
	
	//use case: get all transactions
	@GetMapping("/dataRequest/transactions")
	List<Transaction> allTransaction(){
		return backendService.getTransactions();
	}
	// use case: find transaction by id
	@GetMapping("/dataRequest/transactions/id/{id}")
	Transaction getT(@PathVariable Long id) throws TransactionNotFoundException{
		return backendService.getTransaction(id);
	}

	// use case: create transaction
	@PostMapping("/dataRequest/transaction")
	Transaction newTransaction(@RequestBody Transaction transaction){
		return backendService.createTransaction(transaction);
	}

	// use case: delete transaction
	// //use case: delete client
	@DeleteMapping("/dataRequest/deltransaction/{id}")
	void deleteTransaction(@PathVariable Long id) {
		backendService.deleteTransaction(id);
	}
}