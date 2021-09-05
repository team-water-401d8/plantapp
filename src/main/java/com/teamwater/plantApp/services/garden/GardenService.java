package com.teamwater.plantApp.services.garden;

import com.teamwater.plantApp.models.garden.Garden;

import java.util.List;

public interface GardenService {
    List<Garden> getAllGardensBelongToUser(Long id) ;
    Garden saveGarden(Garden garden) ;
    Garden getGarden(Long id) ;

    void deleteGarden(Garden removedGarden);
}
