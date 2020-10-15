package com.teamwater.plantApp.models.garden;

import com.teamwater.plantApp.models.plant.Plant;
import com.teamwater.plantApp.models.user.AppUser;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    private String gardenName;

    public Garden(){}

    public Garden(String gardenName) {
        this.gardenName = gardenName;
    }

//    --- Garden / User Table RelationShip ---
    @ManyToOne
    public AppUser appUser;    // user should be able to have multiple gardens


//    --- Garden / Plant Table RelationShip ---
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "GardensToPlants",
            joinColumns = { @JoinColumn(name = "gardenId")},
            inverseJoinColumns = {@JoinColumn(name = "plantId")}
    )
    public Set<Plant> plantsInGarden = new HashSet<>();


//    --- add plant methods ---
    public void addPlant(Plant plant) {
        plantsInGarden.add(plant);
    }
    public void removePlant(Plant plant) {
        plantsInGarden.remove(plant);
    }

//    --- getters & setters ---
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGardenName() {
        return gardenName;
    }
    public void setGardenName(String gardenName) {
        this.gardenName = gardenName;
    }
}
