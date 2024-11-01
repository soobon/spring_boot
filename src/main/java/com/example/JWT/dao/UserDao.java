package com.example.JWT.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDao {
    private final static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final static List<UserDetails> USER_DETAILS_LIST= Arrays.asList(
            new User(
                    "he@gmail.com",
                    passwordEncoder.encode("Password"),
//                    "password",
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
            ),
            new User(
                    "hihi@gmail.com",
                    passwordEncoder.encode("password"),
//                    "password",
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            )
    );

    public UserDetails findUserByEmail(String email){
        return USER_DETAILS_LIST
                .stream()
                .filter(user -> user.getUsername().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("hehehe Not Found"));
    }

}
