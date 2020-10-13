package com.teamwater.plantApp.controllers;

import com.teamwater.plantApp.models.plant.Plant;
import org.springframework.stereotype.Controller;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Controller
public class GardenController  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;


}
