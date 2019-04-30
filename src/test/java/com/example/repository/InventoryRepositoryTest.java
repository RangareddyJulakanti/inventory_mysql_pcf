package com.example.repository;

import com.example.domain.EInventory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
public class InventoryRepositoryTest {

    @Autowired
    InventoryRepository inventoryRepository;

    @Test
    public void testAll(){
        EInventory i1=new EInventory();
        i1.setId(1);
        i1.setItemName("Prevencia");
        i1.setCategory("Lens");
        inventoryRepository.save(i1);

        EInventory result=inventoryRepository.findAll().iterator().next();

        Assert.assertEquals("Prevencia",result.getItemName());
        Assert.assertEquals("Lens",result.getCategory());
    }
    @Test
    public void testFindById(){
        EInventory i1=new EInventory();
        i1.setId(2);
        i1.setItemName("Prevencia2");
        i1.setCategory("Lens2");
        inventoryRepository.save(i1);
        EInventory result=inventoryRepository.findAll().iterator().next();
        Assert.assertNotNull(result);
        Assert.assertEquals("Prevencia2",result.getItemName());
        Assert.assertEquals("Lens2",result.getCategory());
    }
    @Test
    public void testDelete(){
        EInventory i1=new EInventory();
        i1.setId(3);
        i1.setItemName("Prevencia3");
        i1.setCategory("Lens3");
        inventoryRepository.save(i1);

        Assert.assertTrue(inventoryRepository.existsById(3));

        inventoryRepository.deleteById(3);

        Assert.assertFalse(inventoryRepository.existsById(3));

    }
}
