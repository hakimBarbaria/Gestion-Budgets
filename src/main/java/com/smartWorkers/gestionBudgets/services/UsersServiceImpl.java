package com.smartWorkers.gestionBudgets.services;

import com.smartWorkers.gestionBudgets.dao.UsersRepository;
import com.smartWorkers.gestionBudgets.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;
    
    @Override
    public void addUser(Users u){
        usersRepository.save(u);
    }

    @Override
    public Users getUserByEmail(String email) {
        return usersRepository.getUsersByEmail(email);
    }

	@Override
	public Users getUsersByName(String name) {
		return usersRepository.getUsersByName(name);
	}
}
