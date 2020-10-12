package com.teamwater.plantApp.controllers;

import com.teamwater.plantApp.models.user.AppUser;
import com.teamwater.plantApp.models.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class AppUserController {

    @Autowired
    AppUserRepository AppUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public String showProfilePage(Principal principal, Model m){
        m.addAttribute("user",principal);
        return "profile";
    }

    @GetMapping("/user/{username}")
    public String showUserDetails(@PathVariable String username, Model userinfo, Principal principal) {
        AppUser user = AppUserRepository.findByUsername(username);
        if (user == null) {
            userinfo.addAttribute("userDoesNotExist", true);
        }
        userinfo.addAttribute("user", user);
        userinfo.addAttribute("principal", principal);
        return "profile";
    }

    @PostMapping("/follow")
    public RedirectView followUser(Principal principal, Long id) {
        AppUser userToFollow = AppUserRepository.getOne(id);
        AppUser follower = AppUserRepository.findByUsername(principal.getName());

        userToFollow.getFollower(follower);
        follower.follow(userToFollow);

        AppUserRepository.save(userToFollow);
        AppUserRepository.save(follower);

        System.out.println("following a new user!" + userToFollow.getUsername());

        return new RedirectView("/user/" + userToFollow.getUsername());
    }

    @PostMapping("/unfollow")
    public RedirectView unfollowUser(Principal principal, Long id) {
        AppUser userToUnfollow = AppUserRepository.getOne(id);
        AppUser follower = AppUserRepository.findByUsername(principal.getName());

        userToUnfollow.removeFollower(follower);
        follower.removeFollow(userToUnfollow);

        AppUserRepository.save(userToUnfollow);
        AppUserRepository.save(follower);

        System.out.println("You unfollowed a user!" + userToUnfollow.getUsername());

        return new RedirectView("/user/" + userToUnfollow.getUsername());
    }

    // Get mapping for each page to render!
    @GetMapping("/about")
    public String renderAbout(Principal principal, Model m) {
        m.addAttribute("principal", principal);
        return "about";
    }
    @GetMapping("/search")
    public String renderSearch(Principal principal, Model m) {
        m.addAttribute("principal", principal);
        return "search";
    }

//    ---- login routes ----
    @GetMapping("/login")
    public String showLoginPage(Principal principal, Model m){
        System.out.println("----------- login route ----------");
        m.addAttribute("user",principal);
        return "login";
    }
    @PostMapping("/login")
    public RedirectView renderLogin(Principal principal, Model m, String username, String password){
        System.out.println("----------- login route ----------");
        m.addAttribute("user", principal);
        
        return new RedirectView("/user/" + username);
    }

//    ---- signup routes ----
    @GetMapping("/signup")
    public String signUpNewUser(Principal principal, Model m) {
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView makeNewUser(HttpServletRequest request,
                                    String username,
                                    String password) throws Exception {
        System.out.println("----------- adding a user to the DB ----------");

        String passwordEncode = passwordEncoder.encode(password);
        AppUser newUser = new AppUser(username, passwordEncode);
        AppUserRepository.save(newUser);

        request.login(username,password);
        return new RedirectView("/user/" + username);
    }
}