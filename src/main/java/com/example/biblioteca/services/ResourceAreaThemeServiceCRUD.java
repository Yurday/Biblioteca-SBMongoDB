package com.example.biblioteca.services;

import com.example.biblioteca.DTOs.ResourceAreaThemeDTO;
import com.example.biblioteca.collections.ResourceAreaTheme;
import com.example.biblioteca.mappers.ResourceAreaThemeMapper;
import com.example.biblioteca.repositories.ResourceAreaThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceAreaThemeServiceCRUD {

    @Autowired
    ResourceAreaThemeRepository resourceAreaThemeRepository;
    ResourceAreaThemeMapper mapper = new ResourceAreaThemeMapper();

    public List<ResourceAreaThemeDTO> getAll (){
        List<ResourceAreaTheme> areaTheme = (List<ResourceAreaTheme>) resourceAreaThemeRepository.findAll();
        return mapper.fromCollectionList(areaTheme);
    }

    public ResourceAreaThemeDTO getById(String id){
        ResourceAreaTheme areaTheme = resourceAreaThemeRepository.findById(id).orElseThrow(() -> new RuntimeException("Área no encontrada"));
        return mapper.fromCollection(areaTheme);
    }

    public ResourceAreaThemeDTO create(ResourceAreaThemeDTO resourceAreaThemeDTO){
        ResourceAreaTheme areaTheme = mapper.fromDTO(resourceAreaThemeDTO);
        return mapper.fromCollection(resourceAreaThemeRepository.save(areaTheme));
    }

    public ResourceAreaThemeDTO modify(ResourceAreaThemeDTO resourceAreaThemeDTO){
        ResourceAreaTheme areaTheme = mapper.fromDTO(resourceAreaThemeDTO);
        resourceAreaThemeRepository.findById(areaTheme.getId()).orElseThrow(() -> new RuntimeException("Área no encontrada"));
        return mapper.fromCollection(resourceAreaThemeRepository.save(areaTheme));
    }

    public void delete(String id){
        resourceAreaThemeRepository.deleteById(id);
    }
}