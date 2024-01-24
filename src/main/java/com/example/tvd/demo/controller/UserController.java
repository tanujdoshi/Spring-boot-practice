package com.example.tvd.demo.controller;

import com.example.tvd.demo.entity.JwtResponse;
import com.example.tvd.demo.entity.User;
import com.example.tvd.demo.exception.UnAuthorizedUserException;
import com.example.tvd.demo.exception.VerificationException;
import com.example.tvd.demo.service.EmailService;
import com.example.tvd.demo.service.UserService;
import com.example.tvd.demo.service.VerificationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user){
        System.out.println("Hey HERE??");
        userService.register(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            String res = userService.loginProcess(user);
            return ResponseEntity.ok(res);
        } catch (UnAuthorizedUserException | VerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping("/access")
    public ResponseEntity<String> access(){
        System.out.println("Hey HERE??");
        return ResponseEntity.ok("Able to access?");
    }
}
