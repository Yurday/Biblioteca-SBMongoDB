package com.example.biblioteca.controllers;

import com.example.biblioteca.DTOs.ResourceDTO;
import com.example.biblioteca.services.ResourceServiceCRUD;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ResourceControllerTest {

    @MockBean
    private ResourceServiceCRUD resourceServiceCRUD;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /resource")
    public void getResources() throws Exception {

        //Arrange
        var resource1 = new ResourceDTO();
        resource1.setId("60de482cdf9e6c6e0576dc75");
        resource1.setResourceType("Libro");
        resource1.setAreaThemeId("Novela");
        resource1.setStatus(true);
        resource1.setName("Cien años de soledad");
        resource1.setDate(LocalDate.now());

        var resource2 = new ResourceDTO();
        resource2.setId("60de495edf9e6c6e1483ji8g");
        resource2.setResourceType("Revista");
        resource2.setAreaThemeId("Economía");
        resource2.setStatus(true);
        resource2.setName("Dinero");
        resource2.setDate(LocalDate.now());

        var resource3 = new ResourceDTO();
        resource3.setId("60de495edf9e6c6e1439ch8m");
        resource3.setResourceType("Revista");
        resource3.setAreaThemeId("Ciencia");
        resource3.setStatus(true);
        resource3.setName("Nasa actual");
        resource3.setDate(LocalDate.now());

        doReturn(Lists.newArrayList(resource1, resource2, resource3)).when(resourceServiceCRUD).getAll();

        //Act && Assert
        mockMvc.perform(get("/resource/lists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is("60de482cdf9e6c6e0576dc75")))
                .andExpect(jsonPath("$[0].name", is("Cien años de soledad")))
                .andExpect(jsonPath("$[0].resourceType", is("Libro")))
                .andExpect(jsonPath("$[1].id", is("60de495edf9e6c6e1483ji8g")))
                .andExpect(jsonPath("$[1].name", is("Dinero")))
                .andExpect(jsonPath("$[1].resourceType", is("Revista")))
                .andExpect(jsonPath("$[2].id", is("60de495edf9e6c6e1439ch8m")))
                .andExpect(jsonPath("$[2].name", is("Nasa actual")))
                .andExpect(jsonPath("$[2].resourceType", is("Revista")));

    }

    @Test
    @DisplayName("POST /resurce")
    public void postResource() throws Exception{
        //Arrange
        ResourceDTO resourcePost = new ResourceDTO();
        resourcePost.setId("60de482cdf9e6c6e0576dc75");
        resourcePost.setResourceType("Libro");
        resourcePost.setAreaThemeId("Novela");
        resourcePost.setStatus(true);
        resourcePost.setName("Cien años de soledad");

        ResourceDTO returnResource = new ResourceDTO();
        returnResource.setId("60de482cdf9e6c6e0576dc75");
        returnResource.setResourceType("Libro");
        returnResource.setAreaThemeId("Novela");
        returnResource.setStatus(true);
        returnResource.setName("Cien años de soledad");

        doReturn(returnResource).when(resourceServiceCRUD).create(any());

        //Act && Assert
        mockMvc.perform(post("/resource/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(resourcePost)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Cien años de soledad")))
                .andExpect(jsonPath("$.resourceType", is("Libro")))
                .andExpect(jsonPath("$.id", is("60de482cdf9e6c6e0576dc75")));
    }

    static String asJsonString(final Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}