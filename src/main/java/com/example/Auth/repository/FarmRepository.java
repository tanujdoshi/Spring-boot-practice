package com.example.Auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Auth.entities.Farms;

@Repository
public interface FarmRepository extends JpaRepository<Farms, Integer> {

    Farms findById(int id);
} 
