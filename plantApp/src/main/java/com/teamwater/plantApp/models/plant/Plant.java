package com.teamwater.plantApp.models.plant;

import com.teamwater.plantApp.models.garden.Garden;

import javax.persistence.*;

@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String plantCommonName;
    public String plantImageUrl;


//    --- Garden / Plant Table relationship ---
    @ManyToOne Garden garden;

//    --- Constructor / Getters / Setters ---
    public Plant(String plantCommonName, String plantImageUrl) {
        this.plantCommonName = plantCommonName;
        this.plantImageUrl = plantImageUrl;
    }

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
