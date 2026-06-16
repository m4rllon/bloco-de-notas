package com.notes.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@CrossOrigin(origins = "*")
public class LoginController {
    @GetMapping("/")
    public String getHome(){
        return "home";
    }
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
}
