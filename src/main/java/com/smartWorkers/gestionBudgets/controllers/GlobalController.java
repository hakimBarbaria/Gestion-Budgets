package com.smartWorkers.gestionBudgets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.smartWorkers.gestionBudgets.entities.Users;
import com.smartWorkers.gestionBudgets.services.CategoriesService;
import com.smartWorkers.gestionBudgets.services.TransactionsService;
import com.smartWorkers.gestionBudgets.services.UserService;
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
  UserService usersService;

  @ModelAttribute("getNotificationsCount")
  public int getNotificationsCount() {
    int notificationsCount = usersService.getNotificationsCount();
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
 /* @ModelAttribute("user")
  public Users getUser() {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
   //  Users user = userService.getUsersByName();
     return user;
  }*/
}