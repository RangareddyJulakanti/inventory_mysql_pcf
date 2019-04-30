package com.example.service;

import com.example.domain.EInventory;
import com.example.model.Inventory;
import com.example.repository.InventoryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceTest {
    @InjectMocks
    private InventoryService inventoryService;
    @Mock
    private InventoryRepository inventoryRepository;
    @Before
    public void setUp(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }
    @Test
    public void testAll(){
        EInventory i1=new EInventory();
        i1.setId(1);
        i1.setItemName("Prevencia");
        i1.setCategory("Lens");
        Iterable<EInventory> iterable=new ArrayList<>();
        ((ArrayList<EInventory>) iterable).add(i1);

        when(inventoryRepository.findAll()).thenReturn(iterable);
        List<Inventory> list=inventoryService.getAll();
        Assert.assertEquals(list.size(),1);
    }
    @Test
    public void testSave() {
        EInventory i1=new EInventory();
        i1.setId(1);
        i1.setItemName("Prevencia");
        i1.setCategory("Lens");

        when(inventoryRepository.save(any(EInventory.class))).thenReturn(i1);

        Inventory s=new Inventory();
        s.setId(1);
        s.setItemName("Prevencia");
        s.setCategory("Lens");

        ResponseEntity responseEntity= inventoryService.saveInventory(s);

        Assert.assertNotNull(responseEntity.getHeaders().get("Location"));
        Assert.assertEquals(201,responseEntity.getStatusCodeValue());
        Assert.assertTrue(responseEntity.getHeaders().get("Location").get(0).contains("/inventory/inventoryId/1"));

    }
    @Test
    public void testDelete(){
        EInventory i1=new EInventory();
        i1.setId(5);
        i1.setItemName("Prevencia");
        i1.setCategory("Lens");
        doNothing().when(inventoryRepository).deleteById(5);
        ResponseEntity res=inventoryService.delete(5);
        Assert.assertEquals(res.getStatusCodeValue(),202);
    }

}
