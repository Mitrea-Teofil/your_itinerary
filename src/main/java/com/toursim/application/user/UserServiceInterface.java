package com.toursim.application.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
}
