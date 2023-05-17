package com.smartWorkers.gestionBudgets.security;

import com.smartWorkers.gestionBudgets.dao.UsersRepository;
import com.smartWorkers.gestionBudgets.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDatailService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = Optional.ofNullable(usersRepository.getUsersByEmail(username));
        return users.map(UserInfoUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found"));
    }
}
