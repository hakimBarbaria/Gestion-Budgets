package com.smartWorkers.gestionBudgets.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartWorkers.gestionBudgets.entities.Budgets;

public interface BudgetsRepository extends JpaRepository<Budgets, Long>{

}
