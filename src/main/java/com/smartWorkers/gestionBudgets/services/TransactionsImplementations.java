package com.smartWorkers.gestionBudgets.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.smartWorkers.gestionBudgets.dao.TransactionsRepository;
import com.smartWorkers.gestionBudgets.entities.Transactions;

@Service
public class TransactionsImplementations implements TransactionsService {

  private TransactionsRepository transactionRepository;

  public TransactionsImplementations(TransactionsRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  @Override
  public List<Transactions> getTransactions() {
    return transactionRepository.findAll();
  }

  @Override
  public Page<Transactions> getTransactionsInPages(int page, int size) {
    return transactionRepository.findAll(PageRequest.of(page, size));
  }

  @Override
  public List<Transactions> findByCategorie(Long categorie_id) {
    return transactionRepository.findByCategorieId(categorie_id);
  }

  @Override
  public Transactions getTransactionById(Long transaction_id) {
    return transactionRepository.findById(transaction_id).get();
  }

  @Override
  public void deleteTransaction(Long id) {
    transactionRepository.deleteById(id);
  }

  @Override
  public void udpateTransaction(Transactions transaction) {
    transactionRepository.save(transaction);
  }

  @Override
  public void addTransaction(Transactions transaction) {
    transactionRepository.save(transaction);
  }
  
  @Override
  public Page<Transactions> filterByType(String Type,int page, int size) {
	  return transactionRepository.findByType(Type,PageRequest.of(page, size));
  }
}
