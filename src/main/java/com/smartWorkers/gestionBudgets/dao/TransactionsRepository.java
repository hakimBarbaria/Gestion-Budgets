package com.smartWorkers.gestionBudgets.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smartWorkers.gestionBudgets.entities.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
	@Query("select t from Transactions t where t.categorie.categorie_id = ?1")
	List<Transactions> findByCategorieId(Long categorie_id);


  Page<Transactions> findByType(String Type, PageRequest pageRequest);
}
