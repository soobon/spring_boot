package com.example.JWT.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/goodbye")
    public ResponseEntity<String> goodbye(){
        return ResponseEntity.ok("Goodbye!");
    }
}
