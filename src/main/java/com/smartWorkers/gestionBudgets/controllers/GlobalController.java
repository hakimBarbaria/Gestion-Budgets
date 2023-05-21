package com.smartWorkers.gestionBudgets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.smartWorkers.gestionBudgets.entities.Notification;
import com.smartWorkers.gestionBudgets.services.CategoriesService;
import com.smartWorkers.gestionBudgets.services.TransactionsService;
import com.smartWorkers.gestionBudgets.services.UsersService;

@ControllerAdvice
public class GlobalController {
  @Autowired
  TransactionsService transactionsService;
  @Autowired
  CategoriesService categorieService;
  @Autowired
  UsersService userService;
  @Autowired
  UsersService usersService;

  @ModelAttribute("getNotificationsCount")
  public int getNotificationsCount() {
    int notificationsCount = usersService.getNotificationsCount();
    return notificationsCount;
  }

  @ModelAttribute("getNotifications")
  public List<Notification> getNotifications() {
    List<Notification> notifications = usersService.getNotifications();
    return notifications;
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
  /*
   * @ModelAttribute("user")
   * public Users getUser() {
   * Authentication authentication =
   * SecurityContextHolder.getContext().getAuthentication();
   * //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
   * // Users user = userService.getUsersByName();
   * return user;
   * }
   */
}