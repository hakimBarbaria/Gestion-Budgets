package com.smartWorkers.gestionBudgets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartWorkers.gestionBudgets.entities.Transactions;
import com.smartWorkers.gestionBudgets.services.TransactionsService;

@Controller
public class ApplicationController {
	@Autowired
	 TransactionsService transactionsService;
	
	
	@RequestMapping("/Transactions")
	public String Transitions(
	ModelMap modelMap,
	@RequestParam(name = "page", defaultValue = "0") int page,
	@RequestParam(name = "size", defaultValue = "1") int size)
	{
	 Page<Transactions> transactions = transactionsService.getTransactionsInPages(page, size);
	modelMap.addAttribute("transactions", transactions);
	modelMap.addAttribute("pages", new int[transactions.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	return "listeTransactions";
	}
	
}
