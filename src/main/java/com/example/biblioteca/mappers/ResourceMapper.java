package com.example.biblioteca.mappers;

import com.example.biblioteca.DTOs.ResourceDTO;
import com.example.biblioteca.entities.Resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResourceMapper {

    //DTO to resource
    public Resource fromDTO(ResourceDTO resourceDTO){
        Resource resource = new Resource();
        resource.setId(resourceDTO.getId());
        resource.setResourceType(resourceDTO.getResourceType());
        resource.setAreaTheme(resourceDTO.getAreaTheme());
        resource.setStatus(resourceDTO.getStatus());
        resource.setName(resourceDTO.getName());
        resource.setDate(resourceDTO.getDate());
        return resource;
    }

    //Collection to ResourceDTO
    public ResourceDTO fromCollection(Resource collection){
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(collection.getId());
        resourceDTO.setResourceType(collection.getResourceType());
        resourceDTO.setAreaTheme(collection.getAreaTheme());
        resourceDTO.setStatus(collection.getStatus());
        resourceDTO.setName(collection.getName());
        resourceDTO.setDate(collection.getDate());
        return resourceDTO;
    }

    //Collection list to DTO list
    public List<ResourceDTO>fromCollectionList(List<Resource> collection){
        if (collection == null){
            return null;
        }

        List<ResourceDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while (listTracks.hasNext()){
            Resource resource = (Resource) listTracks.next();
            list.add(fromCollection(resource));
        }
        return list;
    }
}