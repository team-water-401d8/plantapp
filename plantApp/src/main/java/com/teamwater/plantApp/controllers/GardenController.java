package com.teamwater.plantApp.controllers;

import com.teamwater.plantApp.models.garden.Garden;
import com.teamwater.plantApp.models.garden.GardenRepository;
import com.teamwater.plantApp.models.user.AppUser;
import com.teamwater.plantApp.models.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.Principal;

@Controller
public class GardenController  {

    @Autowired
    AppUserRepository AppUserRepository;

    @Autowired
    GardenRepository gardenRepository;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @PostMapping("/addGarden")
    public RedirectView addNewGarden(String gardenName, long id){
        Garden newGarden = new Garden(gardenName);
        AppUser user = AppUserRepository.getOne(id);

        newGarden.appUser = user;
        gardenRepository.save(newGarden);

        user.userGarden.add(newGarden);
        AppUserRepository.save(user);
        System.out.println("Congrats, you added a new garden to your profile! " + newGarden.getGardenName());

        return new RedirectView("/user/" + user.getUsername());
    }
//    @PostMapping("/removeGarden")
//    public RedirectView removeGarden(String gardenName, Principal principal){
//        AppUser user = AppUserRepository.getOne(id);
//        user.getId();
//
//        gardenRepository.delete(currentGarden);
//        return new RedirectView("/user/" + user.getUsername());
//    }
}
