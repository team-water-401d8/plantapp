package com.teamwater.plantApp.controllers;

import com.teamwater.plantApp.models.garden.Garden;
import com.teamwater.plantApp.models.garden.GardenRepository;
import com.teamwater.plantApp.models.user.AppUser;
import com.teamwater.plantApp.models.user.AppUserRepository;
import com.teamwater.plantApp.services.garden.GardenService;
import com.teamwater.plantApp.services.user.AppUserService;
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
import java.util.List;

@Controller
public class AppUserController {

    @Autowired

    AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    GardenService gardenService;

    //================================= follow + unfollow =============================================================
    @PostMapping("/follow")
    public RedirectView followUser(Principal principal, Long id) {
        AppUser userToFollow = appUserService.getUser(id);
        AppUser follower = appUserService.getUser(principal.getName());
        userToFollow.getFollower(follower);
        follower.follow(userToFollow);
        appUserService.saveUser(userToFollow);
        appUserService.saveUser(follower);
        System.out.println("following a new user!" + userToFollow.getUsername());
        return new RedirectView("/user/" + userToFollow.getUsername());
    }
    @PostMapping("/unfollow")
    public RedirectView unfollowUser(Principal principal, Long id) {
        AppUser userToUnfollow = appUserService.getUser(id);
        AppUser follower = appUserService.getUser(principal.getName());
        userToUnfollow.removeFollower(follower);
        follower.removeFollow(userToUnfollow);
        appUserService.saveUser(userToUnfollow);
        appUserService.saveUser(follower);
        System.out.println("You unfollowed a user!" + userToUnfollow.getUsername());
        return new RedirectView("/user/" + userToUnfollow.getUsername());
    }

    //==================================== about us ============================================================
    @GetMapping("/about")
    public String renderAbout(Principal principal, Model m) {
        m.addAttribute("user", principal);
        return "about";
    }

    //==================================== Login =================================================================
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
        return new RedirectView("/profile");
    }
    //========================================== Sign up =====================================
    @GetMapping("/signup")
    public String signUpNewUser(Principal principal, Model m) {
        return "signup";
    }
    @PostMapping("/signup")
    public RedirectView makeNewUser(HttpServletRequest request,
                                    String username,
                                    String password
    ) throws Exception {
        System.out.println("----------- adding a user to the DB ----------");
        String passwordEncode = passwordEncoder.encode(password);
        AppUser newUser = new AppUser(username, passwordEncode);
        appUserService.saveUser(newUser);
        request.login(username,password);
        return new RedirectView("/user/" + username);
    }
    //============================= user profile ===================================================================
    @GetMapping("/user/{username}")
    public String showUserDetails(@PathVariable String username, Model userinfo, Principal principal) {
        AppUser user = appUserService.getUser(username);
        if (user == null) {
            userinfo.addAttribute("userDoesNotExist", true);
        }
        userinfo.addAttribute("user", user);
        userinfo.addAttribute("principal", principal);


        //        delete and refactor later.
        List<Garden> gardenMess = gardenService.getAllGardensBelongToUser(user.getId());
        userinfo.addAttribute("gardenMess", gardenMess);
        System.out.println(gardenMess.size());



        return "profile";
    }
    @GetMapping("/profile")
    public RedirectView profilePage(Principal principal, Model m){
        System.out.println("------------ profile route -------------");
        m.addAttribute("user",principal);
        return new RedirectView("/user/" + principal.getName());
    }
}