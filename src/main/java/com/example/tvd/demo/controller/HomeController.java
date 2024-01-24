package com.example.tvd.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    @CrossOrigin
    @GetMapping("/access")
    public ResponseEntity<String> access(){
        System.out.println("Hey HERE??");
        return ResponseEntity.ok("Able to access Home?");
    }
}
