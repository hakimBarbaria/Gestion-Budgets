package com.smartWorkers.gestionBudgets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartWorkers.gestionBudgets.entities.Budgets;
import com.smartWorkers.gestionBudgets.entities.Transactions;
import com.smartWorkers.gestionBudgets.services.BudgetsService;
import com.smartWorkers.gestionBudgets.services.CategoriesService;
import com.smartWorkers.gestionBudgets.services.TransactionsService;
import com.smartWorkers.gestionBudgets.services.UserService;

@Controller
public class ApplicationController {
  @Autowired
  TransactionsService transactionsService;
  @Autowired
  CategoriesService categorieService;
  @Autowired
  UserService userService;
  
  @Autowired
  BudgetsService budgetService;

  @RequestMapping("/Dashboard")
  public String RedirectToDashboard(ModelMap modelMap) {
	List <Budgets> budgets = budgetService.getBudgets();
    List<Float> expensesCount = transactionsService.getExpensesCountsByMonth();
    List<Float> incomeCount = transactionsService.getIncomeCountsByMonth();
    int countIncomes = transactionsService.getCountIncomes();
    int countExpenses = transactionsService.getCountExpenses();
    List<Transactions> transactionsOut = this.transactionsService.getLastTransactions("EXPENSE");
    List<Transactions> transactionsIn = this.transactionsService.getLastTransactions("INCOME");
    modelMap.addAttribute("budgets", budgets);
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

  @RequestMapping("/SignUp")
  public String RedirectToSignUp() {
    return "SignUp";
  }

  @RequestMapping("/Profile")
  public String RedirectToProfile(ModelMap modelMap) {
    return "Profile";
  }

  @RequestMapping("/Settings")
  public String RedirectToSettings(ModelMap modelMap) {
    return "Settings";
  }
}
