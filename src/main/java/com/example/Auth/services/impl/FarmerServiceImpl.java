package com.example.Auth.services.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Auth.entities.Farms;
import com.example.Auth.entities.User;
import com.example.Auth.repository.UserRepository;
import com.example.Auth.repository.FarmRepository;
import com.example.Auth.services.FarmerService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FarmerServiceImpl implements FarmerService {
    private final UserRepository userRepository;
    private final FarmRepository farmRepository;

    @Override
    public List<Farms> addFarm(Farms farm, Principal principal){
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();

        farm.setUser(user);
        farmRepository.save(farm);
        return farmRepository.findAll();
        // Farms savedFarm = new Farms();
        // savedFarm.setName(farm.getName());
        // savedFarm.setLat(farm.getLat());
        // savedFarm.setName(farm.);
        // savedFarm.setEmail(signUpRequest.getEmail());
        // user.setFirstname(signUpRequest.getFirstName());
        // user.setSecondname(signUpRequest.getLastName());
        // user.setRole(signUpRequest.getRole());
        // user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    }
    
}
