package com.teamwater.plantApp.services.user;

import com.teamwater.plantApp.models.user.AppUser;

public interface AppUserService {
    AppUser getUser(Long id) ;
    AppUser getUser(String username) ;
    AppUser saveUser(AppUser user) ;
}
