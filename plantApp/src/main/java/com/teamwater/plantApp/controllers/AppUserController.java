package com.teamwater.plantApp.controllers;

import com.teamwater.plantApp.models.user.AppUser;
import com.teamwater.plantApp.models.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

public class AppUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired

//    @GetMapping("/user/{username}")
//    public String showUserDetails(@PathVariable String username, Model userinfo, Principal principal) {
//        AppUser user = appUserRepository.findByUsername(username);
//        if (user == null) {
//            userinfo.addAttribute("userDoesNotExist", true);
//        }
//        userinfo.addAttribute("user", user);
//        userinfo.addAttribute("principal", principal);
//        return "profile";
//    }

    @PostMapping("/follow")
    public RedirectView followUser(Principal principal, Long id) {
        AppUser userToFollow = appUserRepository.getOne(id);
        AppUser follower = appUserRepository.findByUsername(principal.getName());

        userToFollow.getFollower(follower);
        follower.follow(userToFollow);

        appUserRepository.save(userToFollow);
        appUserRepository.save(follower);

        System.out.println("following a new user!" + userToFollow.getUsername());

        return new RedirectView("/user/" + userToFollow.getUsername());
    }

    @PostMapping("/unfollow")
    public RedirectView unfollowUser(Principal principal, Long id) {
        AppUser userToUnfollow = appUserRepository.getOne(id);
        AppUser follower = appUserRepository.findByUsername(principal.getName());

        userToUnfollow.removeFollower(follower);
        follower.removeFollow(userToUnfollow);

        appUserRepository.save(userToUnfollow);
        appUserRepository.save(follower);

        System.out.println("You unfollowed a user!" + userToUnfollow.getUsername());

        return new RedirectView("/user/" + userToUnfollow.getUsername());
    }
    // Getters for each page to render
    @GetMapping("/about")
    public String renderAbout(Principal principal, Model m) {
        m.addAttribute("principal", principal);
        return "about";
    }
    @GetMapping("/profile")
    public String renderProfile(Principal principal, Model m) {
        m.addAttribute("principal", principal);
        return "profile";
    }
    @GetMapping("/search")
    public String renderSearch(Principal principal, Model m) {
        m.addAttribute("principal", principal);
        return "search";
    }
}
