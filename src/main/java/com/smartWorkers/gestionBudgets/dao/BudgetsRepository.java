package com.smartWorkers.gestionBudgets.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smartWorkers.gestionBudgets.entities.Budgets;

public interface BudgetsRepository extends JpaRepository<Budgets, Long>{
	@Query("SELECT b FROM Budgets b WHERE b.categorie.categorie_id = ?1")
	  Budgets findByCategorieId(long idCat);
}
