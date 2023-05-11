package com.smartWorkers.gestionBudgets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartWorkers.gestionBudgets.entities.Transactions;
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
    Long count = transactionsService.numberTransactions();
    Long countC = categorieService.numberCategories();
    List<Transactions> transactionsOut = this.transactionsService.getLastTransactions("EXPENSE");
    List<Transactions> transactionsIn = this.transactionsService.getLastTransactions("INCOME");
    modelMap.addAttribute("nbT", count);
    modelMap.addAttribute("nbC", countC);
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
	  Long count = transactionsService.numberTransactions();
	    Long countC = categorieService.numberCategories();
	    modelMap.addAttribute("nbT", count);
	    modelMap.addAttribute("nbC", countC);
    return "Profile";
  }
  
  @RequestMapping("/Settings")
  public String RedirectToSettings(ModelMap modelMap) {
	  Long count = transactionsService.numberTransactions();
	    Long countC = categorieService.numberCategories();
	    modelMap.addAttribute("nbT", count);
	    modelMap.addAttribute("nbC", countC);
    return "Settings";
  }
}
