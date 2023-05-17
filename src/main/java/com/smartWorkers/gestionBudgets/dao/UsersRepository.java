package com.smartWorkers.gestionBudgets.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartWorkers.gestionBudgets.entities.Users;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, Long>{
    public Users getUsersByEmail(String email);
}
