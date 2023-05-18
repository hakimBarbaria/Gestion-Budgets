package com.smartWorkers.gestionBudgets.services;

import com.smartWorkers.gestionBudgets.entities.Users;

public interface UsersService {
    public void addUser(Users u);
    
    public Users getUserByEmail(String email);
    
    public Users getUsersByName(String name);
}
