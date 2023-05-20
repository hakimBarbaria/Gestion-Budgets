package com.smartWorkers.gestionBudgets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartWorkers.gestionBudgets.entities.Users;
import com.smartWorkers.gestionBudgets.services.UsersServiceImpl;

@Controller
public class userController {
  @Autowired
  UsersServiceImpl usersService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/addUser")
  public String addUser(@RequestParam("name") String name, @RequestParam("email") String email,
      @RequestParam("password") String password) {
    Users users = new Users();
    users.setName(name);
    users.setEmail(email);
    users.setPassword(passwordEncoder.encode(password));
    users.setRole("user");
    usersService.addUser(users);
    return "redirect:/";
  }

  @RequestMapping("/Profile")
  public String redirectToProfile(ModelMap modelMap) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    Users user = usersService.getUsersByName(userDetails.getUsername());
    modelMap.addAttribute("user", user);
    return "Profile";
  }
}
