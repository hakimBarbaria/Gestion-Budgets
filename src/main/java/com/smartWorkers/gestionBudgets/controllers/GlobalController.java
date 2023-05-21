package com.smartWorkers.gestionBudgets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.smartWorkers.gestionBudgets.entities.Notification;
import com.smartWorkers.gestionBudgets.entities.Users;
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
  public int getNotificationsCount(Authentication authentication) {
    try {
      Users currentUser = usersService.getUserById(authentication);
      int notificationsCount = usersService.getNotificationsCount(currentUser.getUser_id());
      return notificationsCount;
    } catch (NullPointerException e) {
      System.out.println(e.getMessage());
    }
    return -1;
  }

  @ModelAttribute("getNotifications")
  public List<Notification> getNotifications(Authentication authentication) {
    try {
      Users currentUser = usersService.getUserById(authentication);
      List<Notification> notifications = usersService.getNotifications(currentUser.getUser_id());
      return notifications;
    } catch (NullPointerException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  @ModelAttribute("getNumberTransactions")
  public long getNumberTransactions(Authentication authentication) {
    try {
      Users currentUser = usersService.getUserById(authentication);
      Long count = transactionsService.numberTransactions(currentUser.getUser_id());
      return count;
    } catch (NullPointerException e) {
      System.out.println(e.getMessage());
    }
    return 0;
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