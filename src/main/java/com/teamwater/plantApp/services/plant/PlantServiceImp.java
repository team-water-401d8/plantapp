package com.teamwater.plantApp.services.plant;

import com.teamwater.plantApp.models.plant.Plant;
import com.teamwater.plantApp.models.plant.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantServiceImp implements PlantService{

    @Autowired
    PlantRepository plantRepository ;

    @Override
    public Plant getPlantFromApi(Long id) {
        return plantRepository.findByPlantIdFromApi(id);
    }

    @Override
    public Plant savePlant(Plant plant) {
        return null;
    }
}
