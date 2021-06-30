package com.example.biblioteca.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document //Document in the collection
public class Resource {

    @Id
    private String id;
    private String areaThemeId;
    private String resourceType;
    private Boolean Status;
    private String name;
    private LocalDate date;

    //Getter

    public String getId() {
        return id;
    }

    public String getAreaThemeId() {
        return areaThemeId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Boolean getStatus() {
        return Status;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }


    //Setter


    public void setId(String id) {
        this.id = id;
    }

    public void setAreaThemeId(String areaThemeId) {
        this.areaThemeId = areaThemeId;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
