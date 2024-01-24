package com.example.tvd.demo.repository;

import com.example.tvd.demo.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    VerificationCode findByCodeAndEmail(String code, String email);
    VerificationCode findByEmail(String email);
}
