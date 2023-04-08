package com.smartWorkers.gestionBudgets.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.smartWorkers.gestionBudgets.entities.Categories;
import com.smartWorkers.gestionBudgets.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
  @Autowired
  CategoriesService categoriesService;
  boolean ChangingTypeOfPresentation = false;
  
  @RequestMapping("/redirectionToOriginalList")
  public String redirectionToOriginalList() {
	  return "redirect:/Transactions";
  }
  
  @RequestMapping("/Dashboard")
  public String RedirectToDashboard () {
	  return "dashboard";
  }
  
  @RequestMapping("/Categories")
  public String RedirectToCategories () {
	  return "categories";
  }
  
  @RequestMapping("/AddTransactions")
  public String RedirectToAddTransaction () {
	  return "AddTransactions";
  }
  
  @RequestMapping("/AddCategory")
  public String RedirectToAddCategory () {
	  return "AddCategories";
  }
  
  @RequestMapping("/AddBudget")
  public String RedirectToAddBudget () {
	  return "AddBudgets";
  }
  
  @RequestMapping("/Budgets")
  public String RedirectToBudgets () {
	  return "Budgets";
  }
  
  @RequestMapping("/ChangingType")
  public String changingType() {
    if (this.ChangingTypeOfPresentation == false) {
      this.ChangingTypeOfPresentation = true;
      
    } else {
      this.ChangingTypeOfPresentation = false;
    }
    return "redirect:/Transactions";
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
    } else {
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
    } else {
      return "listeTransactions";
    }

  }

  @PostMapping("/filterWithCategorie")
  public String filterWithCategory(@RequestParam("categorie") String categorie, ModelMap modelMap) {

    List<Transactions> filteredTransactions = transactionsService.findByCategorie(categorie);
    List<Transactions> ALLtransactions = transactionsService.getTransactions();
    modelMap.addAttribute("transactions", filteredTransactions);
    if (filteredTransactions.isEmpty()) {
      modelMap.addAttribute("message", "You don't have any transactions in this category !");
    }
    modelMap.addAttribute("ALLtransactions", ALLtransactions);
    if (this.ChangingTypeOfPresentation == false) {
      return "listeTransactionsUsingCards";
    } else {
      return "listeTransactions";
    }
  }

  @RequestMapping(path = "/delete/{id}")
  public String deleteTransaction(@PathVariable Long id) {
    transactionsService.deleteTransaction(id);
    return "redirect:/Transactions";
  }

  @RequestMapping("/modifier_transaction")
  public String modifierTransaction(@RequestParam("id") Long transaction_id, ModelMap modelMap) {
    Transactions transaction = transactionsService.getTransactionById(transaction_id);
    modelMap.addAttribute("transaction", transaction);
    return "editTransaction";
  }

  @RequestMapping("/update_transaction")
  public String updateTransaction(@ModelAttribute("transaction") Transactions new_transaction,
      @RequestParam("date") String date, ModelMap modelMap) throws ParseException {
    Long transaction_id = new_transaction.getTransaction_id();
    Transactions old_transaction = transactionsService.getTransactionById(transaction_id);
    System.out.println("this is what you looking for : " + transaction_id);
    if (date != "" && old_transaction.getCreated_at() != new_transaction.getCreated_at()) {
      // conversion de la date
      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
      Date dateCreation = dateformat.parse(String.valueOf(date));
      old_transaction.setCreated_at(dateCreation);

      Date currentDate = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      old_transaction.setUpdated_at(dateFormat.parse(dateFormat.format(currentDate)));
    }
    if (old_transaction.getAmount() != new_transaction.getAmount() && new_transaction.getAmount() > 0) {
      old_transaction.setAmount(new_transaction.getAmount());
    }
    if (old_transaction.getType() != new_transaction.getType() && new_transaction.getType().length() > 0) {
      old_transaction.setType(new_transaction.getType());
    }
    if (old_transaction.getCategorie() != new_transaction.getCategorie()
        && new_transaction.getCategorie().length() > 0D) {
      old_transaction.setCategorie(new_transaction.getCategorie());
    }

    // if (old_transaction.getDescription() != new_transaction.getDescription() &&
    // new_transaction.getDescription().length() > 0D) {
    // old_transaction.setDescription(new_transaction.getDescription());
    // }
    transactionsService.udpateTransaction(old_transaction);

    Transactions updated_Transaction = transactionsService.getTransactionById(transaction_id);
    modelMap.addAttribute("message", "Transaction updated successfully !");
    modelMap.addAttribute("transaction", updated_Transaction);
    return "editTransaction";
  }
  @RequestMapping("/saveTransaction")
  public String saveTransaction(ModelMap modelMap,
                                @ModelAttribute("transaction") Transactions new_transaction,
                                @RequestParam("date") String date,
                                @RequestParam("description") String description
  ) throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dateCreation = dateFormat.parse(date);
    new_transaction.setCreated_at(dateCreation);
    LocalDateTime ldt = LocalDateTime.now();
    String today = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);
    Date tadayForReal = dateFormat.parse(today);
    new_transaction.setUpdated_at(tadayForReal);
    new_transaction.setDescription(description);
    transactionsService.addTransaction(new_transaction);
    return "redirect:/Transactions";
  }
  @RequestMapping("/saveCategory")
  public String saveCategory(ModelMap modelMap,
                             @RequestParam("name") String name,
                             @RequestParam("icon") String icon
  ){
    Categories new_category = new Categories();
    new_category.setName(name);
    new_category.setIcon(icon);
    categoriesService.addCategory(new_category);
    return "redirect:/Dashboard";
  }
}
