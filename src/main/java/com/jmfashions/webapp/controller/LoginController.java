package com.jmfashions.webapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmfashions.webapp.DTO.LoginResponse;
import com.jmfashions.webapp.entity.LoginEntity;
import com.jmfashions.webapp.service.LoginService;




@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

     @GetMapping("/")
    public String home() {
        return "redirect:/Application.html";
    }

    // REGISTER
    @PostMapping("/register")
    public String register(@RequestBody LoginEntity user) {

        return loginService.registerUser(user);
    }

    // LOGIN
   @PostMapping("/login")
public LoginResponse login(@RequestBody LoginRequest request) {

    return loginService.loginUser(
        request.getEmailOrMobile(),
        request.getPassword()
    );
}


    // Login request class
    public static class LoginRequest {

        private String emailOrMobile;
        private String password;

        public String getEmailOrMobile() {
            return emailOrMobile;
        }

        public void setEmailOrMobile(String emailOrMobile) {
            this.emailOrMobile = emailOrMobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}