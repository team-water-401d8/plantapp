package com.teamwater.plantApp.models.garden;


import com.teamwater.plantApp.models.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GardenRepository extends JpaRepository <Garden, Long> {
    List<Garden> findAllByAppUserId(Long id);
}
