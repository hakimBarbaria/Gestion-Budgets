package com.smartWorkers.gestionBudgets.controllers;

import com.smartWorkers.gestionBudgets.entities.Users;
import com.smartWorkers.gestionBudgets.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class userController {
    @Autowired
    UsersServiceImpl usersService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/addUser")
    public String addUser(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("password") String password)
    {
        Users users = new Users();
        users.setName(name);
        users.setEmail(email);
        users.setPassword(passwordEncoder.encode(password));
        users.setRole("user");
        usersService.addUser(users);
        return "redirect:/";
    }
}
