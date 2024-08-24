package com.gangdev.authapi.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gangdev.authapi.jwt.JwtService;
import com.gangdev.authapi.service.AuthService;
import com.gangdev.authapi.user.AppUser;
import com.gangdev.authapi.view.TokenizedAppUser;
import com.gangdev.authapi.xaction.AuthenticateUserXAction;
import com.gangdev.authapi.xaction.CreateAppUserXAction;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/sign-up")
    public ResponseEntity<AppUser> signUp(@RequestBody CreateAppUserXAction xAction) {
        AppUser registeredUser = authService.signup(xAction);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenizedAppUser> signIn(@RequestBody AuthenticateUserXAction xAction) {
        AppUser authenticatedUser = authService.authenticate(xAction);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        TokenizedAppUser loginResponse = new TokenizedAppUser();
        loginResponse.setJwtToken(jwtToken);

        return ResponseEntity.ok(loginResponse);
    }

}
