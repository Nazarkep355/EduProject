package com.example.eduproject.service;

import com.example.eduproject.entity.User;
import com.example.eduproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    public User RegisterUserByForm(String email, String password,String name){
        if(isEmailExist(email)){
            throw new IllegalArgumentException("Email exists");
        }
        User user = User.builder()
                .email(email)
                .password(encoder.encode(password))
                .name(name)
                .build();
        return userRepository.save(user);
    }
    public Boolean isEmailExist(String email){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent();
    }
}
