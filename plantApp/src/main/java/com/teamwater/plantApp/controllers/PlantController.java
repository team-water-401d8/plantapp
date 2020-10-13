package com.teamwater.plantApp.controllers;

import com.teamwater.plantApp.models.plant.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PlantController {

    @Autowired
    PlantRepository plantRepository;




}
