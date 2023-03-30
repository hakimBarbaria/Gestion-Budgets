package com.smartWorkers.gestionBudgets.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.smartWorkers.gestionBudgets.entities.Transactions;

public interface TransactionsService {

  List<Transactions> getTransactions();

  Page<Transactions> getTransactionsInPages(int page, int size);

  List<Transactions> findByCategorie(String categorie);

  void deleteTransaction(Long id);

  public Transactions getTransactionById(Long transaction_id);
}
