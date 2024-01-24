package com.example.tvd.demo.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private String token;

    public String getToken() {
        return token;
    }
}
