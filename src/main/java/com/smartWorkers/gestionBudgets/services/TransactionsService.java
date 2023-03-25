package com.smartWorkers.gestionBudgets.services;

import java.time.Month;
import java.util.List;

import org.springframework.data.domain.Page;

import com.smartWorkers.gestionBudgets.entities.Transactions;

public interface TransactionsService {
	
	List<Transactions> getTransactions();
	Page<Transactions> getTransactionsInPages(int page, int size);
}
