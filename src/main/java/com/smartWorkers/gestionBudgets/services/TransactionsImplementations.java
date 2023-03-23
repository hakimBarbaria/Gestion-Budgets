package com.smartWorkers.gestionBudgets.services;

import java.util.List;

import com.smartWorkers.gestionBudgets.dao.TransactionsRepository;
import com.smartWorkers.gestionBudgets.entities.Transactions;

public class TransactionsImplementations implements TransactionsService{
	
	TransactionsRepository transactionRepository;
	
	public List<Transactions> getTransactions() {

		 return transactionRepository.findAll();
		 }
}
