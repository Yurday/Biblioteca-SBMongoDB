package com.example.biblioteca.DTOs;

import com.example.biblioteca.collections.Resource;

import java.util.List;

public class ResourceListAreaThemeDTO {

private String areaTheme;
private List<Resource> resources;

public ResourceListAreaThemeDTO (){
}

    //Getter

    public String getAreaTheme() {
        return areaTheme;
    }

    public List<Resource> getResources() {
        return resources;
    }


    //Setter

    public void setAreaTheme(String areaTheme) {
        this.areaTheme = areaTheme;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}