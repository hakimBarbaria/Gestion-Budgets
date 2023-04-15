package com.smartWorkers.gestionBudgets.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class budgetController {
  @RequestMapping("/AddBudget")
  public String RedirectToAddBudget() {
    return "AddBudgets";
  }

  @RequestMapping("/Budgets")
  public String RedirectToBudgets() {
    return "Budgets";
  }
}
