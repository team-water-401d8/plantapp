package com.teamwater.plantApp.models.plant;

import com.teamwater.plantApp.models.garden.Garden;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String plantCommonName;
    public String plantImageUrl;
    public Long plantIdFromApi;

//    --- Garden / Plant Table relationship ---
    @ManyToMany(cascade = CascadeType.REMOVE)
    public Set<Garden> listOfGardens = new HashSet<>();


//    --- Constructor / Getters / Setters ---
    public Plant(String plantCommonName, String plantImageUrl, Long plantIdFromApi) {
        this.plantCommonName = plantCommonName;
        this.plantImageUrl = plantImageUrl;
        this.plantIdFromApi = plantIdFromApi;
    }
    public Plant(){}

    public String getPlantCommonName() {
        return plantCommonName;
    }

    public void setPlantCommonName(String plantCommonName) {
        this.plantCommonName = plantCommonName;
    }

    public String getPlantImageUrl() {
        return plantImageUrl;
    }

    public void setPlantImageUrl(String plantImageUrl) {
        this.plantImageUrl = plantImageUrl;
    }
}
