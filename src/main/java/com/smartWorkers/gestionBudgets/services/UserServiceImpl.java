package com.smartWorkers.gestionBudgets.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartWorkers.gestionBudgets.entities.Budgets;
import com.smartWorkers.gestionBudgets.entities.Transactions;

@Service
public class UserServiceImpl implements UserService {
  // private UsersRepository usersRepository;

  // public UserServiceImpl(UsersRepository usersRepository) {
  // this.usersRepository = usersRepository;
  // }

  @Autowired
  TransactionsService transactionsService;
  @Autowired
  BudgetsService budgetsService;

  @Override
  public int getNotificationsCount() {
    int notifications = 0;
    List<Transactions> transactions = transactionsService.getTransactions();
    List<Budgets> budgets = budgetsService.getBudgets();

    for (Budgets budget : budgets) {
      float expensesAmount = 0;
      for (Transactions transaction : transactions) {
        if ((transaction.getCategorie().getCategorie_id() == budget.getCategorie().getCategorie_id())) {
          expensesAmount += transaction.getAmount();
        }
      }
      if (expensesAmount > budget.getbudgetLimit()) {
        notifications++;
      }
    }

    return notifications;
  }
}