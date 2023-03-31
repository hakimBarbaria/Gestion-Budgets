package com.smartWorkers.gestionBudgets.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartWorkers.gestionBudgets.entities.Transactions;
import com.smartWorkers.gestionBudgets.services.TransactionsService;

@Controller
public class ApplicationController {
  @Autowired
  TransactionsService transactionsService;
  boolean ChangingTypeOfPresentation = false;
  
  @RequestMapping("/ChangingType")
  public String changingType() {
	  if (this.ChangingTypeOfPresentation == false) {
		  this.ChangingTypeOfPresentation= true;
		  return "redirect:/Transactions";
	  }else {
		  this.ChangingTypeOfPresentation = false;
		  return "redirect:/Transactions";
	  }
	  
  }
  
  @RequestMapping("/Transactions")
  public String Transitions(
      ModelMap modelMap,
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "3") int size) {
    Page<Transactions> transactions = transactionsService.getTransactionsInPages(page, size);
    List<Transactions> ALLtransactions = transactionsService.getTransactions();
    modelMap.addAttribute("transactions", transactions);
    modelMap.addAttribute("pages", new int[transactions.getTotalPages()]);
    modelMap.addAttribute("currentPage", page);
    modelMap.addAttribute("ALLtransactions", ALLtransactions);
    if (this.ChangingTypeOfPresentation == false) {
    	return "listeTransactionsUsingCards";
    }else {
    	return "listeTransactions";
    }
  }

  @PostMapping("/filteringWithDate")
  public String filterTransactionsByMonth(@RequestParam("month") int month, ModelMap modelMap) {
    // Get the transactions from your service layer
    List<Transactions> transactions = transactionsService.getTransactions();
    List<Transactions> ALLtransactions = transactionsService.getTransactions();
    // Filter the transactions based on the selected month
    List<Transactions> filteredTransactions = transactions.stream()
        .filter(transaction -> {
          // Get the month of the transaction date
          LocalDate transactionDate = transaction.getCreated_at().toInstant().atZone(ZoneId.systemDefault())
              .toLocalDate();
          int transactionMonth = transactionDate.getMonthValue();

          // Compare the month with the selected month
          return transactionMonth == month;
        })
        .collect(Collectors.toList());

    // Add the filtered transactions to the model
    modelMap.addAttribute("transactions", filteredTransactions);
    modelMap.addAttribute("ALLtransactions", ALLtransactions);
    if (filteredTransactions.isEmpty()) {
      modelMap.addAttribute("message", "You don't have any transactions this mounth !");

    }
    if (this.ChangingTypeOfPresentation == false) {
    	return "listeTransactionsUsingCards";
    }else {
    	return "listeTransactions";
    }

  }

  @PostMapping("/filterWithCategorie")
  public String filterWithCategory(@RequestParam("categorie") String categorie, ModelMap modelMap) {

    List<Transactions> filteredTransactions = transactionsService.findByCategorie(categorie);
    List<Transactions> ALLtransactions = transactionsService.getTransactions();
    modelMap.addAttribute("transactions", filteredTransactions);
    modelMap.addAttribute("ALLtransactions", ALLtransactions);
    if (this.ChangingTypeOfPresentation == false) {
    	return "listeTransactionsUsingCards";
    }else {
    	return "listeTransactions";
    }
  }

  @RequestMapping(path = "/delete/{id}")
  public String deleteTransaction(@PathVariable Long id) {
    transactionsService.deleteTransaction(id);
    return "redirect:/Transactions";
  }
}
