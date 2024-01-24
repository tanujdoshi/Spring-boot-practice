package com.example.tvd.demo.service;

import com.example.tvd.demo.entity.JwtResponse;
import com.example.tvd.demo.entity.User;

public interface UserService {
    void register(User user);

    String loginProcess(User user);
}

