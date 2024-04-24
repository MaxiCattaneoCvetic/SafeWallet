package com.example.safewallet.keycloak.DTO;

import java.util.Map;

public class UpdateModelDto {

    private Map<String,String> type;


    public UpdateModelDto(Map<String, String> type) {
        this.type = type;
    }

    public UpdateModelDto() {
    }

    public Map<String, String> getType() {
        return type;
    }

    public void setType(Map<String, String> type) {
        this.type = type;
    }
}
