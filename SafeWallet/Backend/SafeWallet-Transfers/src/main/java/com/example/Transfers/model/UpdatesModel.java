package com.example.Transfers.model;

import java.util.Map;

public class UpdatesModel {

    private Long id;
    private Map<String,String> type;


    public UpdatesModel() {
    }


    public UpdatesModel(Long id, Map<String, String> type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, String> getType() {
        return type;
    }

    public void setType(Map<String, String> type) {
        this.type = type;
    }
}
