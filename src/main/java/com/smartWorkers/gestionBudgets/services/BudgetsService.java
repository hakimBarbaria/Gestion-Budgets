package com.smartWorkers.gestionBudgets.services;

import java.util.List;

import com.smartWorkers.gestionBudgets.entities.Budgets;

public interface BudgetsService {
  public void addBudgets(Budgets budget);

  public List<Budgets> getBudgets();
}
