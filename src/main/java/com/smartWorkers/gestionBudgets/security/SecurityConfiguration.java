package com.smartWorkers.gestionBudgets.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  @Bean
  public UserDetailsService userDetailsService() {
    return new UserInfoUserDatailService();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    String[] staticResources = {
        "/image/**",
    };
    return http.csrf().disable()
        .authorizeHttpRequests().requestMatchers("/").permitAll()
        .and()
        .authorizeHttpRequests().requestMatchers(staticResources).permitAll()
        .and()
        .authorizeHttpRequests().requestMatchers("/login").permitAll()
        .and()
        .authorizeHttpRequests().requestMatchers("/signup").permitAll()
        .and()
        .authorizeHttpRequests().requestMatchers("/Equipe").permitAll()
        .and()
        .authorizeHttpRequests().requestMatchers("/addUser").permitAll()
        .and()
        .authorizeHttpRequests().requestMatchers("/landingPage").permitAll()
        .and()
        .authorizeHttpRequests().requestMatchers("/**").authenticated()
        .and().formLogin().loginPage("/login").loginProcessingUrl("/login")
        .defaultSuccessUrl("/Dashboard", true).permitAll()
        .and().build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }
}
