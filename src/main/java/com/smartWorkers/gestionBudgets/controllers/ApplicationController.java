package com.smartWorkers.gestionBudgets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartWorkers.gestionBudgets.entities.Transactions;
import com.smartWorkers.gestionBudgets.entities.Users;
import com.smartWorkers.gestionBudgets.services.CategoriesService;
import com.smartWorkers.gestionBudgets.services.TransactionsService;
import com.smartWorkers.gestionBudgets.services.UsersService;
import com.smartWorkers.gestionBudgets.services.UsersServiceImpl;

import jakarta.persistence.EntityManager;

@Controller
public class ApplicationController {
  @Autowired
  TransactionsService transactionsService;
  @Autowired
  CategoriesService categorieService;
  @Autowired
  UsersServiceImpl usersService;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

  @Autowired
  UsersService userService;

  @RequestMapping("/Dashboard")
  public String RedirectToDashboard(ModelMap modelMap, Authentication authentication) {
    Users currentUser = userService.getUserById(authentication);

    List<Float> expensesCount = transactionsService.getExpensesCountsByMonth(currentUser.getUser_id());
    List<Float> incomeCount = transactionsService.getIncomeCountsByMonth(currentUser.getUser_id());
    int countIncomes = transactionsService.getCountIncomes(currentUser.getUser_id());
    int countExpenses = transactionsService.getCountExpenses(currentUser.getUser_id());
    List<Transactions> transactionsOut = this.transactionsService.getLastTransactions("EXPENSE",currentUser.getUser_id());
    List<Transactions> transactionsIn = this.transactionsService.getLastTransactions("INCOME",currentUser.getUser_id());

    List<Transactions> transactions = this.transactionsService.getTransactions(currentUser.getUser_id());
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
    currentUser.setBalance(balance);
    modelMap.addAttribute("balance", balance);
    
    modelMap.addAttribute("expensesCount", expensesCount);
    modelMap.addAttribute("incomeCount", incomeCount);
    modelMap.addAttribute("nombreTransactions", countIncomes);
    modelMap.addAttribute("nombreCategories", countExpenses);
    modelMap.addAttribute("transactionsOut", transactionsOut);
    modelMap.addAttribute("transactionsIn", transactionsIn);
    return "dashboard";
  }

  @RequestMapping("/")
  public String RedirectToAcceuille() {
    return "landingPage";
  }

  @RequestMapping("/Equipe")
  public String RedirectToEquipe() {
    return "Equipe";
  }

  @RequestMapping("/signup")
  public String RedirectToSignUp() {
    return "signup";
  }

  @RequestMapping("/Settings")
  public String RedirectToSettings(ModelMap modelMap) {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      Users user = usersService.getUsersByName(userDetails.getUsername());
      modelMap.addAttribute("user", user);
    return "Settings";
  }
  @RequestMapping("/EditUser")
  public String editUser (ModelMap modelMap,@RequestParam("Email") String email, @RequestParam("password") String password
		  ) {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      Users user = usersService.getUsersByName(userDetails.getUsername());
      user.setEmail(email);
      user.setPassword(passwordEncoder.encode(password));
      usersService.editUser(user);
      modelMap.addAttribute("user", user);
      modelMap.addAttribute("msg", "User Info changed Successfully !");
      return "Settings";
  }
  @RequestMapping("/DeleteUser")
  public String deleteUser() {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      Users user = usersService.getUsersByName(userDetails.getUsername());
	  usersService.deleteUser(user.getUser_id());
	  return "redirect:/";
  }
}
