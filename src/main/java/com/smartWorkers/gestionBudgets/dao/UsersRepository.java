package com.smartWorkers.gestionBudgets.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartWorkers.gestionBudgets.entities.Users;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, Long>{
    public Users getUsersByEmail(String email);
    
    @Query("select u from Users u where u.name = ?1")
    public Users getUsersByName(String name);

}

