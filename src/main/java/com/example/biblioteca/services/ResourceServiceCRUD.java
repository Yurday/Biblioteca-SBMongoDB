package com.example.biblioteca.services;

import com.example.biblioteca.DTOs.ResourceDTO;
import com.example.biblioteca.entities.Resource;
import com.example.biblioteca.mappers.ResourceMapper;
import com.example.biblioteca.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceCRUD {

    @Autowired
    ResourceRepository resourceRepository;
    ResourceMapper mapper = new ResourceMapper();

    //Get all resources
    public List<ResourceDTO> getAll(){
        List<Resource> resources = (List<Resource>) resourceRepository.findAll();
        return mapper.fromCollectionList(resources);
    }

    //Get resource by id
    public ResourceDTO getById (String id){
        Resource resource = resourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found"));
        return mapper.fromCollection(resource);
    }

    //Create resource
    public ResourceDTO create(ResourceDTO resourceDTO){
        Resource resource = mapper.fromDTO(resourceDTO);
        return mapper.fromCollection(resourceRepository.save(resource));
    }

    //Modify resource
    public ResourceDTO modify(ResourceDTO resourceDTO){
        Resource resource = mapper.fromDTO(resourceDTO);
        resourceRepository.findById(resource.getId()).orElseThrow(() -> new RuntimeException("Resource not found"));
        return mapper.fromCollection(resourceRepository.save(resource));
    }

    //Delete resource
    public void delete (String id){
        resourceRepository.deleteById(id);
    }
}