package com.smartWorkers.gestionBudgets.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smartWorkers.gestionBudgets.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
  public Users getUsersByEmail(String email);

  @Query("select u from Users u where u.name = ?1")
  public Users getUsersByName(String name);

  }
