package com.teamwater.plantApp.controllers;

import com.teamwater.plantApp.models.user.AppUser;
import com.teamwater.plantApp.models.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class AppUserController {

    @Autowired
    AppUserRepository AppUserRepository;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


//    --- sign up routes ---
    @GetMapping("/signup")
    public String signUpNewUser(Principal principal, Model m) {return ("signup");}

    @PostMapping("/signup")
    public RedirectView makeNewUser(HttpServletRequest request,
                                    String username,
                                    String password) throws Exception {
        System.out.println("----------- adding a user to the DB ----------");

        String passwordEncode = passwordEncoder.encode(password);
        AppUser newUser = new AppUser(username,passwordEncode);
        AppUserRepository.save(newUser);

        request.login(username,password);
        return new RedirectView("/profile");

    }

//    --- profile route ---
    @GetMapping("/profile")
    public String profilePage(Principal principal, Model m){
        System.out.println("------------ profile route -------------");
        m.addAttribute("user",principal);
        return ("profile");

    }
}
