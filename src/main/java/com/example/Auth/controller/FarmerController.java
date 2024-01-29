package com.example.Auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Auth.entities.Farms;
import com.example.Auth.services.FarmerService;

import java.util.Map;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/farmer")
@RequiredArgsConstructor
public class FarmerController {
    
    private final FarmerService farmerService;
    @GetMapping
    public ResponseEntity<String> farmerHome() {
        return ResponseEntity.ok("Hi Farmer");
    }
    
    @PostMapping("/addfarm")
    public ResponseEntity<Map> addFarm(@RequestBody Farms farm, Principal principal) {
        System.out.println("Principallll:" + principal.getName());
        List<Farms> AllFarmerFarms = farmerService.addFarm(farm, principal);
        Map<String, Object> response = new HashMap<>();

        response.put("message", principal.getName());
        response.put("Farmsss", AllFarmerFarms);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
