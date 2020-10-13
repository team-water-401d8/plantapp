package com.teamwater.plantApp.controllers;

import com.google.gson.Gson;
import com.teamwater.plantApp.models.garden.GardenRepository;
import com.teamwater.plantApp.models.plant.Plant;
import com.teamwater.plantApp.models.plant.PlantRepository;
import com.teamwater.plantApp.models.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    GardenRepository gardenRepository;

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    AppUserRepository appUserRepository;

    //How are we getting info from API?

    //We need to determine what parameters users can search by. Start with common name and whether edible
    //common name string; is edible boolean;

    //Query the API based on user input. API rate limit of 120 reqs/min. Therefore, we should save plant object in our
    //database, rather than continuing to query Trefle.

    //A JSON object returns a given plant. We'll make a list of parsed plants from those JSON objects. Add this to page model

    //Render page
    @GetMapping("/newsearch")
    public String startNewSearch(){
        return "search";
    }

    @GetMapping("/search")
    public String searchForPlant(@RequestParam(required = false) String q){
        //Setting up the API url. q represents the user input (a string) that is added to the API query.
        String apiUrl = "https://trefle.io/api/v1/plants/search?token=" + System.getenv("TREFLE_API_KEY");
        if(q != null){
            apiUrl += "&q=" + q;
        }
        Plant[] capturedPlant;
        System.out.println(apiUrl);

        try {
            capturedPlant = callApi(apiUrl);

        } catch (Exception exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }


        return "search";
    }

    public Plant[] callApi(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        Gson gson = new Gson();
        dataDTO plantDto = gson.fromJson(reader, dataDTO.class);

        reader.close();
        return plantDto.plantDto;
    }

    class dataDTO{
       public Plant[] plantDto;
    }
}


