package com.example.biblioteca.DTOs;

import java.time.LocalDate;

public class ResourceDTO {

    private String id;
    private String resourceType;
    private String areaThemeId;
    private Boolean status;
    private String name;
    private LocalDate date;

    //Builder
    public ResourceDTO(){
    }

    //Getter

    public String getId() {
        return id;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getAreaThemeId() {
        return areaThemeId;
    }

    public Boolean getStatus() {
        return status;
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

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setAreaThemeId(String areaThemeId) {
        this.areaThemeId = areaThemeId;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
