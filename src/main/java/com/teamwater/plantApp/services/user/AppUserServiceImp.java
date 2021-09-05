package com.teamwater.plantApp.services.user;

import com.teamwater.plantApp.models.user.AppUser;
import com.teamwater.plantApp.models.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImp implements AppUserService{

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public AppUser getUser(Long id) {
        return appUserRepository.findById(id).orElseThrow();
    }

    @Override
    public AppUser getUser(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        return appUserRepository.save(user);
    }
}
