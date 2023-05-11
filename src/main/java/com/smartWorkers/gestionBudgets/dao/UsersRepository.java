package com.smartWorkers.gestionBudgets.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartWorkers.gestionBudgets.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}