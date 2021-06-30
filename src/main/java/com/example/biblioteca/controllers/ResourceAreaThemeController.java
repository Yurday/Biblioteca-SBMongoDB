package com.example.biblioteca.controllers;

import com.example.biblioteca.DTOs.ResourceAreaThemeDTO;
import com.example.biblioteca.services.ResourceAreaThemeServiceCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/area")
public class ResourceAreaThemeController {

    @Autowired
    ResourceAreaThemeServiceCRUD resourceAreaThemeServiceCRUD;

    @GetMapping("/{id}")
    public ResponseEntity<ResourceAreaThemeDTO> findById(@PathVariable("id") String id){
        return new ResponseEntity(resourceAreaThemeServiceCRUD.getById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResourceAreaThemeDTO> findAll(){
        return new ResponseEntity(resourceAreaThemeServiceCRUD.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResourceAreaThemeDTO> create(@RequestBody ResourceAreaThemeDTO resourceAreaThemeDTO){
        return new ResponseEntity(resourceAreaThemeServiceCRUD.create(resourceAreaThemeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/modify")
    public ResponseEntity<ResourceAreaThemeDTO> update(@RequestBody ResourceAreaThemeDTO resourceAreaThemeDTO) {
        if (resourceAreaThemeDTO.getId() != null) {
            return new ResponseEntity(resourceAreaThemeServiceCRUD.modify(resourceAreaThemeDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            resourceAreaThemeServiceCRUD.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}