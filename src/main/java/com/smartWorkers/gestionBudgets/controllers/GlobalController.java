package com.smartWorkers.gestionBudgets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.smartWorkers.gestionBudgets.services.CategoriesService;
import com.smartWorkers.gestionBudgets.services.TransactionsService;
import com.smartWorkers.gestionBudgets.services.UserService;

@ControllerAdvice
public class GlobalController {
  @Autowired
  TransactionsService transactionsService;
  @Autowired
  CategoriesService categorieService;
  @Autowired
  UserService userService;

  @ModelAttribute("getNotificationsCount")
  public int getNotificationsCount() {
    int notificationsCount = userService.getNotificationsCount();
    return notificationsCount;
  }

  @ModelAttribute("getNumberTransactions")
  public long getNumberTransactions() {
    Long count = transactionsService.numberTransactions();
    return count;
  }

  @ModelAttribute("getNumberCategories")
  public long getNumberCategories() {
    Long countC = categorieService.numberCategories();
    return countC;
  }
}