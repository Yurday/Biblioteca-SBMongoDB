package com.example.biblioteca.controllers;

import com.example.biblioteca.DTOs.ResourceDTO;
import com.example.biblioteca.services.ResourceServiceCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping ("/resource")

public class ResourceController {

    @Autowired
    ResourceServiceCRUD resourceServiceCRUD;

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> findById(@PathVariable("id") String id){
        return new ResponseEntity(resourceServiceCRUD.getById(id),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ResourceDTO>> findAll(){
        return new ResponseEntity(resourceServiceCRUD.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResourceDTO> create(@RequestBody ResourceDTO resourceDTO){
        return new ResponseEntity(resourceServiceCRUD.create(resourceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/modify")
    public ResponseEntity<ResourceDTO> update(@RequestBody ResourceDTO resourceDTO){
        if(resourceDTO.getId() != null){
            return new ResponseEntity(resourceServiceCRUD.modify(resourceDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id){
        try {
            resourceServiceCRUD.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}