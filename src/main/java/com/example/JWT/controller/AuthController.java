package com.example.JWT.controller;

import com.example.JWT.dto.AuthenticationRequest;
import com.example.JWT.service.JwtService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request
            ){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        if (user != null){
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("authorities", user.getAuthorities());

            return ResponseEntity.ok(jwtService.generateToken(extraClaims,user));
        }
        return ResponseEntity.status(400).body("Error");
    }
}
