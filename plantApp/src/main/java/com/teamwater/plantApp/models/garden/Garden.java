package com.teamwater.plantApp.models.garden;

import com.teamwater.plantApp.models.plant.Plant;
import com.teamwater.plantApp.models.user.AppUser;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name="GARDENS")
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
    AppUser user;    // user should be able to have multiple gardens


//    --- Garden / Plant Table RelationShip ---
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Plant> gardenPlants = new HashSet<>();

//    --- add plant methods ---
    public void addPlant(Plant plant) {gardenPlants.add(plant);}
    public void removePlant(Plant plant) {gardenPlants.remove(plant);}


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
