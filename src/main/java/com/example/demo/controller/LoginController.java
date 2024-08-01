package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.server.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

   

    @GetMapping("/login")
    public String login(HttpSession session) {
        session.invalidate();  
        return "login";  
    }

 
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";  
    }

   
}
