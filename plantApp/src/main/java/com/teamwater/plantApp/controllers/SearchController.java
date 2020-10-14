package com.teamwater.plantApp.controllers;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.teamwater.plantApp.models.garden.GardenRepository;
import com.teamwater.plantApp.models.plant.Plant;
import com.teamwater.plantApp.models.plant.PlantRepository;
import com.teamwater.plantApp.models.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    //Do we need line 49 and 50? Not sure if it's redundant -mw/pc
    @GetMapping("/newsearch")
    public String startNewSearch(){
        return "search";
    }

    @GetMapping("/search")
    public String searchForPlant(
            Model m,
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String page
    ){
        //Setting up the API url. q represents the user input (a string) that is added to the API query.
        String apiUrl = "https://trefle.io/api/v1/plants/search?token=" + System.getenv("TREFLE_API_KEY") + "&filter_not[common_name]";
        if (q != null){
            apiUrl += "&q=" + q;
            System.out.println(q);
        }
        if (page != null) { // Address the offset of numbers per page (have gotten ranging values from 18 to 20)
            apiUrl += "&page=" + page;
        }
        PlantDTO[] capturedPlants;
        System.out.println(apiUrl);
        apiUrl = apiUrl.replace(" ", "%20");

        try {
            capturedPlants = callApi(apiUrl);
            m.addAttribute("plants", capturedPlants);
        } catch (Exception exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }


        return "search";
    }

    // helper method that calls the api and returns an array of the plants uding DTO's to capture all data (can be adjsuted for efficiency)
    private PlantDTO[] callApi(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        Gson gson = new Gson();
        DataDTO plantDto = gson.fromJson(reader, DataDTO.class);

        reader.close();
        return plantDto.data;
    }

    class DataDTO{
       public PlantDTO[] data;
    }

    class PlantDTO {
            public Long id;
            public String common_name;
            public String slug;
            public String scientific_name;
            public Long year;
            public String bibliography;
            public String author;
            public String status;
            public String rank;
            public String family_common_name;
            public Long genus_id;
            public String image_url;
            public String[] synonyms;
            public String genus;
            public String family;

//            "links": {
//            "self": "/api/v1/species/allium-fistulosum",
//            "plant": "/api/v1/plants/allium-fistulosum",
//            "genus": "/api/v1/genus/allium" }
        }
    }


