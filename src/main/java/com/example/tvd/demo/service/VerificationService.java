package com.example.tvd.demo.service;

public interface VerificationService {
    void sendVerificationEmail(String email);
    void verify(String code, String email);
}
