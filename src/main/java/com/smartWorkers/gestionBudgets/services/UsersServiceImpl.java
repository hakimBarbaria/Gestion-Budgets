package com.smartWorkers.gestionBudgets.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartWorkers.gestionBudgets.dao.UsersRepository;
import com.smartWorkers.gestionBudgets.entities.Budgets;
import com.smartWorkers.gestionBudgets.entities.Notification;
import com.smartWorkers.gestionBudgets.entities.Transactions;
import com.smartWorkers.gestionBudgets.entities.Users;

@Service
public class UsersServiceImpl implements UsersService {
  @Autowired
  UsersRepository usersRepository;

  @Autowired
  TransactionsService transactionsService;

  @Autowired
  BudgetsService budgetsService;

  @Override
  public void addUser(Users u) {
    usersRepository.save(u);
  }

  @Override
  public Users getUserByEmail(String email) {
    return usersRepository.getUsersByEmail(email);
  }

  @Override
  public Users getUsersByName(String name) {
    return usersRepository.getUsersByName(name);
  }

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

  @Override
  public List<Notification> getNotifications() {
    Double amount;
    List<Notification> notifications = new ArrayList<>();
    List<Transactions> transactions = transactionsService.getTransactions();
    List<Budgets> budgets = budgetsService.getBudgets();

    for (Budgets budget : budgets) {
      Notification notification = new Notification();
      float expensesAmount = 0;
      for (Transactions transaction : transactions) {
        if ((transaction.getCategorie().getCategorie_id() == budget.getCategorie().getCategorie_id())) {
          expensesAmount += transaction.getAmount();
        }
      }
      if (expensesAmount > budget.getbudgetLimit()) {
        amount = expensesAmount - budget.getbudgetLimit();
        notification.setDate(budgets.get(budgets.size() - 1).getUpdated_at());
        notification.setCategory(budget.getCategorie());
        notification.setMessage("You have exceeded your budget by <b> " + amount
            + " DT</b>. Please review your expenses and consider adjusting your spending in this category to stay within your budget.");
        notifications.add(notification);
      }
    }

    return notifications;
  }
}
