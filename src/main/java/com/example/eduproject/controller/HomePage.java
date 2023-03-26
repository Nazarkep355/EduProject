package com.example.eduproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomePage {

    @GetMapping("/")
    public String homePage(HttpServletRequest request){
        String username = request.getRemoteUser();
        if(username==null){
            return "login_page.html";
        }
        else return "redirect:/groups";
    }
    @PostMapping("/log")
    public String redirHome(){
        return "redirect:/";
    }
}
