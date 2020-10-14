package com.teamwater.plantApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Model m, Principal principal){
        m.addAttribute("user", principal);
        return "home";
    }
}
