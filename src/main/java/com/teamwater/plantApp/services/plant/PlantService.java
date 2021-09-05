package com.teamwater.plantApp.services.plant;

import com.teamwater.plantApp.models.plant.Plant;

public interface PlantService {
    Plant getPlantFromApi(Long id) ;
    Plant savePlant(Plant plant);
}
