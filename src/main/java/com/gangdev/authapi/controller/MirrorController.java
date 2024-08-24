package com.gangdev.authapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gangdev.authapi.jwt.JwtService;
import com.gangdev.authapi.view.AppUserSimpleView;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/mirror")
public class MirrorController {

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ResponseEntity<AppUserSimpleView> getMirror(HttpServletRequest request) {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userLogin;

        jwt = authHeader.substring(7);
        userLogin = jwtService.extractUsername(jwt);
        if (userLogin != null) {
            AppUserSimpleView user = new AppUserSimpleView();
            user.setLogin(userLogin);
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }
}
