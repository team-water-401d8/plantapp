package com.teamwater.plantApp.controllers;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.teamwater.plantApp.models.garden.GardenRepository;
import com.teamwater.plantApp.models.plant.PlantRepository;
import com.teamwater.plantApp.models.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;

@Controller
public class DetailController {
    @Autowired
    GardenRepository gardenRepository;

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/detail/{plantIdFromApi}")
    //We should add Principal p to the params in below method if we want user name to persist in page display.
    public String renderedPlantFromSearch(@PathVariable String plantIdFromApi, Model m, Principal principal) {
        String apiUrlDetailQuery = "https://trefle.io/api/v1/species/" + plantIdFromApi + "?token=" + System.getenv("TREFLE_API_KEY");
        PlantDetailDto plant;
        try {
            plant = callApi(apiUrlDetailQuery);
        }

        catch(IOException e) {
            System.out.println(e.getMessage());
            return "error";
        }
        m.addAttribute("plant", plant);
        m.addAttribute("user", principal);
        return "detail";
    }


    private PlantDetailDto callApi(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        Gson gson = new Gson();
        DataDto dto = gson.fromJson(reader, DataDto.class);

        reader.close();
        return dto.data;
    }

    class PlantDetailDto {

//        public Long regionCode;
        public Long id;
        @SerializedName(value="common_name")
        public String commonName;
        @SerializedName(value="scientific_name")
        public String scientificName;
        @SerializedName(value="edible")
        public Boolean isPlantEdible;
        @SerializedName(value="edible_part")
        public String[] ediblePart;
        public String image_url;
        public PlantImageContainerDto images;
    }

    class DataDto {
        public PlantDetailDto data;
    }
    class PlantImageDto{
        public Long id;
        public String image_url;
        public String copyright;
    }

    class PlantImageContainerDto{
        public PlantImageDto[] flower;
        public PlantImageDto[] leaf;
        public PlantImageDto[] habit;
        public PlantImageDto[] fruit;
        public PlantImageDto[] bark;
        public PlantImageDto[] other;
    }

}
