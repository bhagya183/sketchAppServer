package com.bhagyashri.sketchapp.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bhagyashri.sketchapp.entity.User;
import com.bhagyashri.sketchapp.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return  this.userRepository.findByEmail(email).map(user->{return new User(
        			user.getEmail(),
        			user.getPassword(),
        			user.getRoles().stream().map(role ->new SimpleGrantedAuthority(role)).collect(Collectors.toList())
        		);}).orElseThrow(
        					()->{
        						throw new UsernameNotFoundException("User with this email is not found");
        					}
        				);
           
    }
}
