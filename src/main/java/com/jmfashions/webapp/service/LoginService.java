package com.jmfashions.webapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jmfashions.webapp.entity.LoginEntity;
import com.jmfashions.webapp.repository.LoginRepository;

import com.jmfashions.webapp.DTO.LoginResponse;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    // REGISTER
    public String registerUser(LoginEntity user) {

        // Check email already exists
        if (loginRepository.existsByEmail(user.getEmail())) {
            return "Email already registered";
        }

        // Check mobile already exists
        if (loginRepository.existsByMobile(user.getMobile())) {
            return "Mobile number already registered";
        }

        // Save user
        loginRepository.save(user);

        return "Registration successful";
    }

    // LOGIN
   public LoginResponse loginUser(String emailOrMobile, String password) {

    Optional<LoginEntity> user = loginRepository.findByEmail(emailOrMobile);

    if (user.isEmpty()) {
        user = loginRepository.findByMobile(emailOrMobile);
    }

    if (user.isEmpty()) {
        return new LoginResponse("User not found", null);
    }

    if (!user.get().getPassword().equals(password)) {
        return new LoginResponse("Invalid password", null);
    }

    return new LoginResponse(
        "Login successful",
        user.get().getName()
    );
}

}