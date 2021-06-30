package com.example.biblioteca.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ResourceAreaTheme {

    @Id
    private String id;
    private String name;

    //Getter

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //Setter

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
