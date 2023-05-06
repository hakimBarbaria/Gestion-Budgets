package com.smartWorkers.gestionBudgets.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.smartWorkers.gestionBudgets.entities.Transactions;

public interface TransactionsService {

  public List<Transactions> getTransactions();

  public int getCountIncomes();
  
  public int getCountExpenses();
  
  public Page<Transactions> getTransactionsInPages(int page, int size);

  public List<Transactions> findByCategorie(Long categorie_id);

  public void deleteTransaction(Long id);

  public Transactions getTransactionById(Long transaction_id);

  public void udpateTransaction(Transactions transaction);

  public void addTransaction(Transactions transaction);

  public Page<Transactions> filterByType(String Type, int page, int size);

  public List<Float> getExpensesCountsByMonth();

  public List<Float> getIncomeCountsByMonth();
  
  public Long numberTransactions();

}
