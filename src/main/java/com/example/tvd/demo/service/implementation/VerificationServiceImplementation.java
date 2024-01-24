package com.example.tvd.demo.service.implementation;

import com.example.tvd.demo.entity.User;
import com.example.tvd.demo.entity.VerificationCode;
import com.example.tvd.demo.repository.UserRepository;
import com.example.tvd.demo.repository.VerificationCodeRepository;
import com.example.tvd.demo.service.EmailService;
import com.example.tvd.demo.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VerificationServiceImplementation implements VerificationService {
    private EmailService emailService;
    private UserRepository userRepository;
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    public VerificationServiceImplementation(EmailService emailService, UserRepository userRepository,VerificationCodeRepository verificationCodeRepository){
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.verificationCodeRepository = verificationCodeRepository;
    }

    @Override
    public void verify(String code, String email)  {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("User not found");
        }
        VerificationCode verificationCode = verificationCodeRepository.findByCodeAndEmail(code, email);
        if(verificationCode == null) {
            System.out.println("Invalid verification code");
        }

        if (verificationCode.getExpiryTime().isBefore(LocalDateTime.now())) {
            System.out.println("Verification code has expired");
        }

        user.setVerified(true);
        userRepository.save(user);
        verificationCodeRepository.delete(verificationCode);
    }
    @Override
    public void sendVerificationEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            System.out.println("User not found");
        }

        if(user.isVerified()) {
            System.out.println("User is already verified");
        }

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);

        if(verificationCode != null && verificationCode.getExpiryTime().isBefore(LocalDateTime.now())) {
            verificationCodeRepository.delete(verificationCode);
            verificationCode = null;
        }

        // Create a new code if no code exists or if it has expired (and hence removed)
        if(verificationCode == null) {
            String code = UUID.randomUUID().toString();
            LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(30);
            verificationCode = new VerificationCode(code, user.getEmail(), expiryTime);
            verificationCodeRepository.save(verificationCode);
        }

        String url="http://localhost:8080/verify?email=%s&code=%s";
        String verificationUrl = String.format(
                url,
                user.getEmail(),
                verificationCode.getCode()
        );
        String subject = "Verify your email";
        String body = "Please click on this link to verify your email: " + verificationUrl;
        emailService.sendEmail("doshitanuj3012@gmail.com", subject, body);
    }
}
