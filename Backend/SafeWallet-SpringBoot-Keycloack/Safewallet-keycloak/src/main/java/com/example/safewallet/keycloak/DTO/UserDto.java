package com.example.safewallet.keycloak.DTO;

import lombok.Builder;


import java.util.Set;



@Builder // permite contruir el objeto

public class UserDto {

    private String id;
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
        /*
     private String firstName;
     private String lastName;*/

    public UserDto() {
    }

    public UserDto(String id, String username, String password, String email, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public UserDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserDto(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
