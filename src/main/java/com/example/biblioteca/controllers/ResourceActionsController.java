package com.example.biblioteca.controllers;


import com.example.biblioteca.DTOs.ResourceAnswerDTO;
import com.example.biblioteca.DTOs.ResourceDTO;
import com.example.biblioteca.DTOs.ResourceListAreaThemeDTO;
import com.example.biblioteca.services.ResourceActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceActionsController {

    @Autowired
    ResourceActionsService resourceActionsService;

    @GetMapping("/availability/{id}")
    public ResponseEntity<ResourceAnswerDTO> availabilityResource(@PathVariable("id") String id){
        return new ResponseEntity(resourceActionsService.availability(id), HttpStatus.OK);
    }

    @GetMapping("/lend/{id}")
    public ResponseEntity<ResourceAnswerDTO> lendResource(@PathVariable("id") String id){
        return new ResponseEntity(resourceActionsService.lendResource(id), HttpStatus.OK);
    }

    @GetMapping("/return/{id}")
    public ResponseEntity<ResourceAnswerDTO> returnService(@PathVariable("id") String id){
        return new ResponseEntity(resourceActionsService.returnResource(id), HttpStatus.OK);
    }

    @GetMapping("/recommend/{id}")
    public ResponseEntity <ResourceListAreaThemeDTO> recommend(@PathVariable("id") String areaThemeId){
        return new ResponseEntity(resourceActionsService.recommendResourceAreaTheme(areaThemeId), HttpStatus.OK);
    }

    @GetMapping("/recommend/type/{id}")
    public ResponseEntity <List<ResourceDTO>> recommendType(@PathVariable("id") String typeResource){
        return new ResponseEntity(resourceActionsService.recommendResourceType(typeResource), HttpStatus.OK);
    }

    @GetMapping("/recommend/area/type/{area}/{type}")
    public ResponseEntity <List<ResourceDTO>> recommendType(@PathVariable("areaTheme") String areaTheme, @PathVariable("typeResource") String typeResource){
        return new ResponseEntity(resourceActionsService.recommendResourceAreaAndType(areaTheme, typeResource), HttpStatus.OK);
    }
}