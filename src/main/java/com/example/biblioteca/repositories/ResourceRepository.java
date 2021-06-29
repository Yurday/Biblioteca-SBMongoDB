package com.example.biblioteca.repositories;

import com.example.biblioteca.entities.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResourceRepository extends MongoRepository<Resource, String> {
}