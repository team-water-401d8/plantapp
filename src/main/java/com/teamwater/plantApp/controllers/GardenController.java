package com.teamwater.plantApp.controllers;

import com.teamwater.plantApp.models.garden.Garden;
import com.teamwater.plantApp.models.garden.GardenRepository;
import com.teamwater.plantApp.models.user.AppUser;
import com.teamwater.plantApp.models.user.AppUserRepository;
import com.teamwater.plantApp.services.garden.GardenService;
import com.teamwater.plantApp.services.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.Principal;

@Controller
public class GardenController  {

    @Autowired
    AppUserService appUserService;

    @Autowired
    GardenService gardenService;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @PostMapping("/addGarden")
    public RedirectView addNewGarden(String gardenName, long id){
        Garden newGarden = new Garden(gardenName);
        AppUser user = appUserService.getUser(id);

        newGarden.appUser = user;
        gardenService.saveGarden(newGarden);

        user.userGarden.add(newGarden);
        appUserService.saveUser(user);
        System.out.println("Congrats, you added a new garden to your profile! " + newGarden.getGardenName());

        return new RedirectView("/user/" + user.getUsername());
    }

    /**
     *
     * @param id of the garden that should be removed
     * @param principal of the current logged in user
     * @return redirect it
     */
    @PostMapping("/removeGarden/{id}")
    public RedirectView removeGarden(@PathVariable Long id , Principal principal){
        AppUser user = appUserService.getUser(principal.getName());
        Garden removedGarden = gardenService.getGarden(id) ;
        gardenService.deleteGarden(removedGarden);
        return new RedirectView("/user/" + user.getUsername());
    }
}
