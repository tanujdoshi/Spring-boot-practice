package com.example.Auth.services;

import java.security.Principal;
import java.util.List;
import com.example.Auth.entities.Farms;


public interface FarmerService {
    List<Farms> addFarm(Farms farm, Principal principal);
}
