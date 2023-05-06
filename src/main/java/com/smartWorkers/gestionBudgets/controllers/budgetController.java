package com.smartWorkers.gestionBudgets.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartWorkers.gestionBudgets.entities.Budgets;
import com.smartWorkers.gestionBudgets.entities.Categories;
import com.smartWorkers.gestionBudgets.entities.Transactions;
import com.smartWorkers.gestionBudgets.services.BudgetsService;
import com.smartWorkers.gestionBudgets.services.CategoriesService;

@Controller
public class budgetController {
	@Autowired
	CategoriesService categorieService;
	@Autowired
	BudgetsService budgetsService;
	
  @RequestMapping("/AddBudget")
  public String RedirectToAddBudget(ModelMap modelMap) {
	  List<Categories> categories = categorieService.getCategories();
	  modelMap.addAttribute("categories", categories);
    return "AddBudgets";
  }
  
  @PostMapping("/saveBudget")
  public String saveTransaction(ModelMap modelMap,
	      @ModelAttribute("budget") Budgets new_budget
	      ) throws ParseException {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    LocalDateTime ldt = LocalDateTime.now();
	    String today = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);
	    Date tadayForReal = dateFormat.parse(today);
	    new_budget.setCreated_at(tadayForReal);
	    new_budget.setUpdated_at(tadayForReal);
	    this.budgetsService.addBudgets(new_budget);
	    modelMap.addAttribute("msg", "Budget Fixed Succesfully");
	    return "AddBudgets";
	  }


  @RequestMapping("/Budgets")
  public String RedirectToBudgets() {
    return "Budgets";
  }
}
