package com.safewallet.userDataService.model;


import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class UserDto {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String dni;


    private String cbu;
    private String alias;


    public UserDto(long id, String name, String lastName, String email, String phone, String cbu, String alias, String dni) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.cbu = cbu;
        this.alias = alias;
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public UserDto() {
    }

    public UserDto(String name, String lastName, String email, String phone,String dni) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dni = dni;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
