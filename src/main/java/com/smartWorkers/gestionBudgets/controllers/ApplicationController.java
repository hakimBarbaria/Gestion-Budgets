package com.smartWorkers.gestionBudgets.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartWorkers.gestionBudgets.entities.Categories;
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
    List<Transactions> ALLtransactions = transactionsService.getTransactions();
    List<Categories> Categories = categorieService.getCategories();
    
    int CC=0;
    Iterator it= Categories.iterator();
    while(it.hasNext()) {
    	CC++;
    	it.next();
    }
    
    int C=0;
    Iterator it2= ALLtransactions.iterator();
    while(it2.hasNext()) {
    	C++;
    	it2.next();
    }
    modelMap.addAttribute("expensesCount", expensesCount);
    modelMap.addAttribute("incomeCount", incomeCount);
    modelMap.addAttribute("nombreTransactions",C);
    modelMap.addAttribute("nombreCategories",CC);
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
