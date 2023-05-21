package com.smartWorkers.gestionBudgets.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.smartWorkers.gestionBudgets.entities.Users;

public class UserInfoUserDetails implements UserDetails {
  private Long user_id;
  private String name;

  private String pwd;
  private List<SimpleGrantedAuthority> authorities;

  public UserInfoUserDetails(Users userInfo) {
    user_id = userInfo.getUser_id();
    name = userInfo.getName();
    pwd = userInfo.getPassword();
    authorities = Arrays.stream(userInfo.getRole().split(",")).map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  @Override
  public String getPassword() {
    return this.pwd;
  }

  @Override
  public String getUsername() {
    return this.name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
