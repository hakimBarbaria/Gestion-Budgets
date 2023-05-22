package com.smartWorkers.gestionBudgets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartWorkers.gestionBudgets.entities.Transactions;
import com.smartWorkers.gestionBudgets.entities.Users;
import com.smartWorkers.gestionBudgets.services.TransactionsService;
import com.smartWorkers.gestionBudgets.services.UsersServiceImpl;

@Controller
public class userController {
  @Autowired
  UsersServiceImpl usersService;
  @Autowired
  TransactionsService transactionsService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/addUser")
  public String addUser(@RequestParam("name") String name, @RequestParam("email") String email,
      @RequestParam("password") String password) {
    Users users = new Users();
    users.setName(name);
    users.setEmail(email);
    users.setPassword(passwordEncoder.encode(password));
    users.setRole("user");
    usersService.addUser(users);
    return "redirect:/";
  }

  @RequestMapping("/Profile")
  public String redirectToProfile(ModelMap modelMap) {

    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      Users user = usersService.getUsersByName(userDetails.getUsername());
      List<Transactions> transactions = this.transactionsService.getTransactions(user.getUser_id());
      Double income = 0.0;
      Double expenses = 0.0;
      for (Transactions transaction : transactions) {
      	System.out.println(transaction.getType());
      	if (transaction.getType().equals("INCOME")) {
      		income = income + transaction.getAmount();
      		System.out.println(income);
      	}else if (transaction.getType().equals("EXPENSE")){
      		expenses = expenses + transaction.getAmount();
      	}
      }
      if (expenses < 0) {
      	expenses = 0.0;
      }
      if (income < 0) {
      	income = 0.0;
      }
      Double balance = 0.0;
      balance = income - expenses;
      user.setBalance(balance);
      modelMap.addAttribute("balance", balance);
      modelMap.addAttribute("user", user);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return "Profile";
  }
}
