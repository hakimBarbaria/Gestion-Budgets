package com.smartWorkers.gestionBudgets.dao;

import java.time.Month;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smartWorkers.gestionBudgets.entities.Transactions;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long>{
	List<Transactions> findByCategorie(String categorie);
}
