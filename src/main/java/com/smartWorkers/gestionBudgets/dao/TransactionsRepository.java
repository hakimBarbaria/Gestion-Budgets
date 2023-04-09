package com.smartWorkers.gestionBudgets.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.smartWorkers.gestionBudgets.entities.Categories;
import com.smartWorkers.gestionBudgets.entities.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
  List<Transactions> findByCategorie(String categorie);
  Page<Transactions> findByType(String Type, PageRequest pageRequest);
}
