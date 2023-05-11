package com.smartWorkers.gestionBudgets.services;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  // private UsersRepository usersRepository;

  // public UserServiceImpl(UsersRepository usersRepository) {
  // this.usersRepository = usersRepository;
  // }

  @Override
  public int getNotificationsCount() {
    return 5;
  }
}