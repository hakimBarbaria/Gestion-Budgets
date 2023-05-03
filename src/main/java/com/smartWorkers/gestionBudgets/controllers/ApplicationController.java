package com.smartWorkers.gestionBudgets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartWorkers.gestionBudgets.services.CategoriesService;
import com.smartWorkers.gestionBudgets.services.TransactionsService;

@Controller
public class ApplicationController {
  @Autowired
  TransactionsService transactionsService;
  @Autowired
  CategoriesService categorieService;

  @RequestMapping("/Dashboard")
  public String RedirectToDashboard(ModelMap modelMap) {
    List<Float> expensesCount = transactionsService.getExpensesCountsByMonth();
    List<Float> incomeCount = transactionsService.getIncomeCountsByMonth();
    int countIncomes = transactionsService.getCountIncomes();
    int countExpenses = transactionsService.getCountExpenses();

    modelMap.addAttribute("expensesCount", expensesCount);
    modelMap.addAttribute("incomeCount", incomeCount);
    modelMap.addAttribute("nombreTransactions", countIncomes);
    modelMap.addAttribute("nombreCategories", countExpenses);
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

  @RequestMapping("/Settings")
  public String RedirectToSettings() {
    return "Settings";
  }
}
