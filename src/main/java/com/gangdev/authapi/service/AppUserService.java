package com.gangdev.authapi.service;

import org.springframework.stereotype.Service;

import com.gangdev.authapi.repository.AppUserRepository;
import com.gangdev.authapi.user.AppUser;

@Service
public class AppUserService {

    private AppUserRepository userRepository;

    public AppUserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser findByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    public AppUser findById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}
