package com.smartWorkers.gestionBudgets.services;

import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.smartWorkers.gestionBudgets.dao.TransactionsRepository;
import com.smartWorkers.gestionBudgets.entities.Transactions;

@Service
public class TransactionsImplementations implements TransactionsService{
	
	private TransactionsRepository transactionRepository;
	public TransactionsImplementations(TransactionsRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public void deleteTransaction(Long id) {
		transactionRepository.deleteById(id);
	}

	public List<Transactions> getTransactions() {
		 return transactionRepository.findAll();
	}
	
	public Page<Transactions> getTransactionsInPages(int page, int size) {
		return transactionRepository.findAll(PageRequest.of(page, size));
	}
	
	public List<Transactions> findByCategorie(String categorie) {
	    return transactionRepository.findByCategorie(categorie);
	}
	
}
