package com.example.tvd.demo.service.implementation;

import com.example.tvd.demo.entity.JwtResponse;
import com.example.tvd.demo.entity.User;
import com.example.tvd.demo.exception.UnAuthorizedUserException;
import com.example.tvd.demo.exception.VerificationException;
import com.example.tvd.demo.service.UserService;
import com.example.tvd.demo.service.VerificationService;
import com.example.tvd.demo.util.JWTUtil;
import org.mindrot.jbcrypt.BCrypt;
import com.example.tvd.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private JWTUtil jwtUtils;
    private VerificationService verificationService;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, JWTUtil jwtUtils, VerificationService verificationService) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.verificationService = verificationService;
    }
    @Override
    public void register(User user) {

        String password = user.getPassword();
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(passwordHash);
        userRepository.save(user);
        verificationService.sendVerificationEmail("doshitanuj3012@gmail.com");

    }

    @Override
    public String loginProcess(User user) throws UnAuthorizedUserException {
        User tempUser;
        tempUser = userRepository.findByEmail(user.getEmail());


        System.out.println("HEREEE 1");
        if (user.getEmail() != "") {
            String passwordToCheck = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            System.out.println("passwordToCheck"+ passwordToCheck);
            Boolean password = BCrypt.checkpw(user.getPassword(), tempUser.getPassword());
            System.out.println("HEREEE ?");
            System.out.println(password);
            if (password) {
                if(user.isVerified() == true) {
                    return "Bearer " + jwtUtils.generateToken(user);
                } else {
                    throw new VerificationException("please varify your email");
                }

            } else {
                throw new UnAuthorizedUserException("Wrong username or password");
            }
        } else {
            throw new UnAuthorizedUserException("Wrong username or password");
        }
    }

}
