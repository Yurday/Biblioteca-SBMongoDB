package com.example.biblioteca.services;


import com.example.biblioteca.DTOs.ResourceAnswerDTO;
import com.example.biblioteca.DTOs.ResourceDTO;
import com.example.biblioteca.DTOs.ResourceListAreaThemeDTO;
import com.example.biblioteca.collections.Resource;
import com.example.biblioteca.collections.ResourceAreaTheme;
import com.example.biblioteca.mappers.ResourceMapper;
import com.example.biblioteca.repositories.ResourceAreaThemeRepository;
import com.example.biblioteca.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ResourceActionsService {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ResourceAreaThemeRepository resourceAreaThemeRepository;

    ResourceMapper mapper = new ResourceMapper();

    public ResourceAnswerDTO availability(String id){
        ResourceAnswerDTO answerDTO = new ResourceAnswerDTO();

        Resource resource = resourceRepository.findById(id).orElseThrow(() -> {throw new IllegalArgumentException("Recurso no encontrado");});
        if (resource.getStatus()){
            answerDTO.setAnswer("El recurso: ( "+mapper.fromCollection(resource).getName()+" ) se encuentra disponible.");
            answerDTO.setStatus(true);
            answerDTO.setDate(null);
            return answerDTO;
        }

        answerDTO.setAnswer("Recurso no disponible.\nÚltima fecha de préstamo: ( "+mapper.fromCollection(resource).getDate().toString()+" )");
        answerDTO.setStatus(false);
        answerDTO.setDate(mapper.fromCollection(resource).getDate());
        return answerDTO;
    }

    public ResourceAnswerDTO lendResource(String id){
        ResourceAnswerDTO answerDTO = new ResourceAnswerDTO();
        LocalDate date = LocalDate.now();
        Resource resource = resourceRepository.findById(id).orElseThrow(() -> {throw new IllegalArgumentException("Recurso no encontrado");});
        if (resource.getStatus()){
            resource.setStatus(false);
            resource.setDate(date);
            resourceRepository.save(resource);
            answerDTO.setAnswer("El recurso: ("+mapper.fromCollection(resource).getName()+" ) ha sido prestado.");
            answerDTO.setStatus(false);
            answerDTO.setDate(mapper.fromCollection(resource).getDate());
            return answerDTO;
        }

        answerDTO.setAnswer("El recurso: ( "+mapper.fromCollection(resource).getName()+" ) no se encuentra disponible.");
        answerDTO.setStatus(false);
        answerDTO.setDate(mapper.fromCollection(resource).getDate());
        return answerDTO;
    }

    public ResourceAnswerDTO returnResource(String id){
        ResourceAnswerDTO answerDTO = new ResourceAnswerDTO();
        LocalDate date = LocalDate.now();
        Resource resource = resourceRepository.findById(id).orElseThrow(() -> {throw new IllegalArgumentException("Recurso no encontrado");});
        if(!resource.getStatus()){
            resource.setStatus(true);
            resource.setDate(date);
            resourceRepository.save(resource);
            answerDTO.setAnswer("El recurso: ( "+mapper.fromCollection(resource).getName()+" ) ha sido regresado.");
            answerDTO.setStatus(true);
            answerDTO.setDate(mapper.fromCollection(resource).getDate());
            return answerDTO;
        }
        answerDTO.setAnswer("El recurso: ( "+mapper.fromCollection(resource).getName()+" ) no esta en préstamo.");
        answerDTO.setStatus(false);
        answerDTO.setDate(mapper.fromCollection(resource).getDate());
        return answerDTO;
    }

    public ResourceListAreaThemeDTO recommendResourceAreaTheme(String areaThemeId){
        ResourceListAreaThemeDTO resourceListAreaThemeDTO = new ResourceListAreaThemeDTO();

        ResourceAreaTheme areaTheme = resourceAreaThemeRepository.findById(areaThemeId).orElseThrow(() -> new RuntimeException("Área no encontrada"));
        List<Resource> resourcesAreaTheme= (List<Resource>) resourceRepository.findByAreaThemeId(areaThemeId).orElseThrow(()->{throw new IllegalArgumentException("No se encontraron registros asociados");});

        resourceListAreaThemeDTO.setAreaTheme(areaTheme.getName());
        resourceListAreaThemeDTO.setResources(resourcesAreaTheme);

        return resourceListAreaThemeDTO;
    }

    public List<ResourceDTO> recommendResourceType(String resourceType){
        List<Resource> resources = resourceRepository.findByResourceType(resourceType).orElseThrow(()->new RuntimeException("Tipo de servicio no encontrado"));
        return mapper.fromCollectionList(resources);
    }

    public List<ResourceDTO> recommendResourceAreaAndType(String areaTheme, String resourceType){
        List<Resource> resources = resourceRepository.findByAreaThemeIdAndResourceType(areaTheme, resourceType).orElseThrow(()->new RuntimeException("Recurso no encontrado"));
        return mapper.fromCollectionList(resources);
    }
}