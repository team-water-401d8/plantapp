package com.teamwater.plantApp.models.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    private String username;
    private String password;
    private String firstName;
//    private String lastName;
//    private String bio;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "follows",
            joinColumns = { @JoinColumn(name = "follower")},
            inverseJoinColumns = {@JoinColumn(name = "following")}
    )
    Set<AppUser> following = new HashSet<>();

    @ManyToMany(mappedBy = "following")
    Set<AppUser> followers = new HashSet<>();

    public AppUser(){}

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.bio = bio;
    }

//    --- follow methods ---
    public void removeFollower(AppUser follower) {
        followers.remove(follower);
    }

    public void removeFollow(AppUser userToUnfollow) {
        following.remove(userToUnfollow);
    }

    public void follow(AppUser userToFollow) {
        following.add(userToFollow);
    }
    public void getFollower(AppUser follower) {
        followers.add(follower);
    }
    public Set<AppUser> getFollowing() {
        return following;
    }
    public void setFollowing(Set<AppUser> following) {
        this.following = following;
    }

    public void setFollowers(Set<AppUser> followers) {
        this.followers = followers;
    }

    public Set<AppUser> getFollowers() {
        return followers;
    }

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
    public Collection<? extends GrantedAuthority> getAuthorities(){
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
