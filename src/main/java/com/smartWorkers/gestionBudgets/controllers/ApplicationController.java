package com.smartWorkers.gestionBudgets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartWorkers.gestionBudgets.entities.Transactions;
import com.smartWorkers.gestionBudgets.services.TransactionsService;

@Controller
public class ApplicationController {
	@Autowired
	 TransactionsService transactionsService;
	
	@RequestMapping("/Transactions")
	public String listeProduits(ModelMap modelMap)
	 {
	 List<Transactions> transactions = transactionsService.getTransactions();
	 modelMap.addAttribute("transactions", transactions);
	 return "listeTransactions";
	 } 
}
