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
        resource1.setId("");
        resource1.setResourceType("");
        resource1.setAreaThemeId("");
        resource1.setStatus(true);
        resource1.setName("");
        resource1.setDate(LocalDate.now());

        var resource2 = new ResourceDTO();
        resource2.setId("");
        resource2.setResourceType("");
        resource2.setAreaThemeId("");
        resource2.setStatus(true);
        resource2.setName("");
        resource2.setDate(LocalDate.now());

        doReturn(Lists.newArrayList(resource1, resource2)).when(resourceServiceCRUD).getAll();

        //Act && Assert
        mockMvc.perform(get("resource/lists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("[0].id", is("")))
                .andExpect(jsonPath("[0].name", is("")))
                .andExpect(jsonPath("[0].resourceType", is("")))
                .andExpect(jsonPath("[1].id", is("")))
                .andExpect(jsonPath("[1].name", is("")))
                .andExpect(jsonPath("[1].resourceType", is("")));
    }

    @Test
    @DisplayName("POST /resurce")
    public void postResource() throws Exception{
        //Arrange
        ResourceDTO resourcePost = new ResourceDTO();
        resourcePost.setId("");
        resourcePost.setResourceType("");
        resourcePost.setAreaThemeId("");
        resourcePost.setStatus(true);
        resourcePost.setName("");

        ResourceDTO returnResource = new ResourceDTO();
        returnResource.setId("");
        returnResource.setResourceType("");
        returnResource.setAreaThemeId("");
        returnResource.setStatus(true);
        returnResource.setName("");

        doReturn(returnResource).when(resourceServiceCRUD).create(any());

        //Act && Assert
        mockMvc.perform(post("/resource/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(resourcePost)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("")))
                .andExpect(jsonPath("$.resourceType", is("")))
                .andExpect(jsonPath("$.id", is("")));
    }

    static String asJsonString(final Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}