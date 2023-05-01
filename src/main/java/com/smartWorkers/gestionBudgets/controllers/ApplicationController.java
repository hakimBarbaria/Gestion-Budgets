package com.smartWorkers.gestionBudgets.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

  @RequestMapping("/Dashboard")
  public String RedirectToDashboard() {
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
}
