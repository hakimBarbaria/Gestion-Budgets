package com.smartWorkers.gestionBudgets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.smartWorkers.gestionBudgets.services.UserService;

@Controller
public class UserController {
  @Autowired
  UserService userService;
}
