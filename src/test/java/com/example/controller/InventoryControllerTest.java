package com.example.controller;

import com.example.model.Inventory;
import com.example.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class InventoryControllerTest {
    MockMvc mockMvc;

    @InjectMocks
    InventoryController inventoryController;
    @Mock
    InventoryService inventoryService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(inventoryController);
        mockMvc=MockMvcBuilders.standaloneSetup(inventoryController).build();
    }
    @Test
    public void testFindAll() throws Exception {
        Inventory inventory=new Inventory();
        inventory.setItemName("Prevencia");
        inventory.setCategory("Lens");
        inventory.setId(1);

        when(inventoryService.getAll()).thenReturn(Arrays.asList(inventory));
        mockMvc.perform(get("/inventory/all")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void testSave() throws Exception {
        Inventory inventory=new Inventory();
        inventory.setItemName("Prevencia");
        inventory.setCategory("Lens");
        inventory.setId(1);
        ResponseEntity responseEntity=   ResponseEntity.status(HttpStatus.CREATED).header("Location","/inventory/inventoryId/1").build();
        when(inventoryService.saveInventory(any(Inventory.class))).thenReturn(responseEntity);
        String given=new ObjectMapper().writeValueAsString(inventory);
        MvcResult result= mockMvc.perform(
                        post("/inventory/save")
                         .contentType(MediaType.APPLICATION_JSON_VALUE).content(given)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andReturn();
        Assert.assertTrue(result.getResponse().getHeader("Location").contains("/inventory/inventoryId/1"));
    }
    @Test
    public void testDelete() throws Exception {
        ResponseEntity responseEntity=   ResponseEntity.status(HttpStatus.ACCEPTED).build();
        when(inventoryService.delete(1)).thenReturn(responseEntity);
        MvcResult result= mockMvc.perform(
                delete("/inventory/inventoryId/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
                .andDo(print())
                .andExpect(status().is(HttpStatus.ACCEPTED.value())).andReturn();
    }
}
