package com.king.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
    @RequestMapping("/")
    public String hello(Model model) {
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.MEM);
            model.addAttribute("role",SaveLogged.MEM.getRole());
            return "home";
        }else {
            return "home";
        }
    }
}
