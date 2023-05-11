package com.smartWorkers.gestionBudgets.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartWorkers.gestionBudgets.dao.BudgetsRepository;
import com.smartWorkers.gestionBudgets.entities.Budgets;

@Service
public class BudgetsServiceImpl implements BudgetsService {

  @Autowired
  BudgetsRepository budgetsRepository;

  @Override
  public void addBudgets(Budgets budget) {
    budgetsRepository.save(budget);
  }

  @Override
  public List<Budgets> getBudgets() {
    return budgetsRepository.findAll();
  }

}
