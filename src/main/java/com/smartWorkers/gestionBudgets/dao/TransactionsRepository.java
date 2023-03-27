package com.smartWorkers.gestionBudgets.dao;

import java.time.Month;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smartWorkers.gestionBudgets.entities.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Long>{
	List<Transactions> findByCategorie(String categorie);
}
