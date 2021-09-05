package com.teamwater.plantApp.services.garden;

import com.teamwater.plantApp.models.garden.Garden;
import com.teamwater.plantApp.models.garden.GardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenServiceImp implements GardenService {

    @Autowired
    GardenRepository gardenRepository ;

    @Override
    public List<Garden> getAllGardensBelongToUser(Long id) {
        return gardenRepository.findAllByAppUserId(id);
    }

    @Override
    public Garden saveGarden(Garden garden) {
        return gardenRepository.save(garden);
    }

    @Override
    public Garden getGarden(Long id) {
        return gardenRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteGarden(Garden garden) {
        gardenRepository.delete(garden) ;
    }
}
