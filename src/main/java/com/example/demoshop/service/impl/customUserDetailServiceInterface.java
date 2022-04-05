package com.example.demoshop.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface customUserDetailServiceInterface {

    UserDetails loadUserByUsername(String login) throws UsernameNotFoundException;
}
