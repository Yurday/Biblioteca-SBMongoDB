package com.example.biblioteca.mappers;

import com.example.biblioteca.DTOs.ResourceAreaThemeDTO;
import com.example.biblioteca.collections.ResourceAreaTheme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResourceAreaThemeMapper {

    //DTO to ResourceAreaTheme

    public ResourceAreaTheme fromDTO(ResourceAreaThemeDTO resourceAreaThemeDTO){
        ResourceAreaTheme areaTheme = new ResourceAreaTheme();
        areaTheme.setId(resourceAreaThemeDTO.getId());
        areaTheme.setName(resourceAreaThemeDTO.getName());

        return areaTheme;
    }

    //Collection to ResourceAreaThemeDTO

    public ResourceAreaThemeDTO fromCollection(ResourceAreaTheme collection){
        ResourceAreaThemeDTO areaThemeDTO = new ResourceAreaThemeDTO();
        areaThemeDTO.setId(collection.getId());
        areaThemeDTO.setName(collection.getName());

        return areaThemeDTO;
    }

    //Collection list to DTO list
    public List<ResourceAreaThemeDTO> fromCollectionList(List<ResourceAreaTheme> collection){
        if (collection == null){
            return null;
        }

        List<ResourceAreaThemeDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while (listTracks.hasNext()){
            ResourceAreaTheme areaTheme = (ResourceAreaTheme) listTracks.next();
            list.add(fromCollection(areaTheme));
        }

        return list;
    }

}