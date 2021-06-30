package com.example.biblioteca.repositories;

import com.example.biblioteca.collections.ResourceAreaTheme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceAreaThemeRepository extends MongoRepository <ResourceAreaTheme, String> {
}
