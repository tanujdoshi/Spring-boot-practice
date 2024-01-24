package com.example.tvd.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class VerificationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String email;
    private LocalDateTime expiryTime;

    public VerificationCode(String code, String email, LocalDateTime expiryTime) {
        this.code = code;
        this.email = email;
        this.expiryTime = expiryTime;
    }

    public VerificationCode() {

    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public String getCode() {
        return code;
    }

}
