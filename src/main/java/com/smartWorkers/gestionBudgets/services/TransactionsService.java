package com.smartWorkers.gestionBudgets.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.smartWorkers.gestionBudgets.entities.Transactions;

public interface TransactionsService {

  public List<Transactions> getTransactions(long user_id);

  public int getCountIncomes(long user_id);
  
  public int getCountExpenses(long user_id);
  
  public Page<Transactions> getTransactionsInPages(int page, int size, long user_id);

  public List<Transactions> findByCategorie(Long categorie_id, long user_id);

  public void deleteTransaction(Long id);

  public Transactions getTransactionById(Long transaction_id);

  public void udpateTransaction(Transactions transaction);

  public void addTransaction(Transactions transaction);

  public Page<Transactions> filterByType(String Type, int page, int size, long user_id);

  public List<Float> getExpensesCountsByMonth(long user_id);

  public List<Float> getIncomeCountsByMonth(long user_id);
  
  public Long numberTransactions(long user_id);
  
  public List<Transactions> getLastTransactions(String Type, long idUser);

}
