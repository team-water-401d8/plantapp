package com.teamwater.plantApp.controllers;

import com.teamwater.plantApp.models.garden.GardenRepository;
import com.teamwater.plantApp.models.plant.PlantRepository;
import com.teamwater.plantApp.models.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class PlantController {

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    AppUserRepository AppUserRepository;

    @Autowired
    GardenRepository gardenRepository;

//  ======== routes =======
    @PostMapping("/addPlantToGarden")
    @ResponseStatus(value= HttpStatus.OK)
    public void addPlantToGarden(@RequestParam(value="common_name")String common_name,
                                 @RequestParam(value="image_url")String image_url){

        //how do i know which user I am logged in as
        //how do i know which gardens the user has

        System.out.println(common_name);
        System.out.println(image_url);
        System.out.println("------ added PLANT TO DB ------");
    }

}
