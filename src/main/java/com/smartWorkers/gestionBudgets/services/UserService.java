package com.smartWorkers.gestionBudgets.services;

import java.util.List;

import com.smartWorkers.gestionBudgets.entities.Notification;

public interface UserService {
  public int getNotificationsCount();

  public List<Notification> getNotifications();
}
