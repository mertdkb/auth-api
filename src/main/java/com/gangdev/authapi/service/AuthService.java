package com.gangdev.authapi.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gangdev.authapi.repository.AppUserRepository;
import com.gangdev.authapi.user.AppUser;
import com.gangdev.authapi.xaction.AuthenticateUserXAction;
import com.gangdev.authapi.xaction.CreateAppUserXAction;

@Service
public class AuthService {
    private final AppUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(AppUserRepository userRepository, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AppUser signup(CreateAppUserXAction xAction) {
        AppUser user = new AppUser();
        user.setLogin(xAction.getLogin());
        user.setPassword(passwordEncoder.encode(xAction.getPassword()));

        return userRepository.save(user);
    }

    public AppUser authenticate(AuthenticateUserXAction xAction) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        xAction.getLogin(),
                        xAction.getPassword()));

        return userRepository.findByLogin(xAction.getLogin())
                .orElseThrow();
    }
}