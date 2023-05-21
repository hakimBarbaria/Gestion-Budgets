package com.smartWorkers.gestionBudgets.services;

import java.util.List;

import com.smartWorkers.gestionBudgets.entities.Notification;
import com.smartWorkers.gestionBudgets.entities.Users;

public interface UsersService {
  public void addUser(Users u);

  public Users getUserById(Long id);

  public Users getUserByEmail(String email);

  public Users getUsersByName(String name);

  public int getNotificationsCount();

  public List<Notification> getNotifications();
}
