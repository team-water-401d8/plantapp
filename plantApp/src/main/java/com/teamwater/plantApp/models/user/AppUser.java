package com.teamwater.plantApp.models.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table (uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    private String username;
    private String password;

    public AppUser(){}

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

//    --- follow methods ---
//    public void removeFollower(ApplicationUser follower) {
//        followers.remove(follower);
//    }
//
//    public void removeFollow(ApplicationUser userToUnfollow) {
//        following.remove(userToUnfollow);
//    }

//    public void follow(ApplicationUser userToFollow) {
//        following.add(userToFollow);
//    }
//    public void getFollower(ApplicationUser follower) {
//        followers.add(follower);
//    }


//    --- getters & setters ---
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
