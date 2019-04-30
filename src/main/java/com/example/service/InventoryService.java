package com.example.service;

import com.example.domain.EInventory;
import com.example.model.Inventory;
import com.example.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAll() {
        List<Inventory> list=new ArrayList<>();
        inventoryRepository.findAll().iterator().forEachRemaining(e-> list.add(convertToInventory(e)));
        return list;
    }


    public ResponseEntity saveInventory(Inventory inventory) {
        EInventory saved = inventoryRepository.save(convertToEInventory(inventory));
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/inventory/inventoryId/" + saved.getId()).buildAndExpand().toUri();
        return ResponseEntity.created(uri).build();
    }

    public Inventory findById(Integer inventoryId) {
        Optional<EInventory> eInventory = inventoryRepository.findById(inventoryId);
        return convertToInventory(eInventory.get());
    }

    public ResponseEntity update(Inventory inventory) {
        Optional<EInventory> oEInventory = inventoryRepository.findById(inventory.getId());
        if (oEInventory.isPresent()) {
            EInventory eInventory = convertToEInventory(inventory);
            inventoryRepository.save(eInventory);
            URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/inventory/inventoryId/" + inventory.getId()).buildAndExpand().toUri();
            return ResponseEntity.created(uri).build();
        } else {
            throw new EntityNotFoundException("Entity not found exception for the Id" + inventory.getId());
        }

    }
    public ResponseEntity delete(Integer inventoryId) {
       inventoryRepository.deleteById(inventoryId);
       return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    private EInventory convertToEInventory(Inventory eInventory) {
        EInventory inventory = new EInventory();
        inventory.setId(eInventory.getId());
        inventory.setItemName(eInventory.getItemName());
        inventory.setCategory(eInventory.getCategory());
        return inventory;
    }

    private Inventory convertToInventory(EInventory eInventory) {
        Inventory inventory = new Inventory();
        inventory.setId(eInventory.getId());
        inventory.setItemName(eInventory.getItemName());
        inventory.setCategory(eInventory.getCategory());
        return inventory;
    }


}
