package com.example.controller;

import com.example.model.Inventory;
import com.example.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @GetMapping("/all")
    public List<Inventory> getAll(){
        return inventoryService.getAll();
    }
    @PostMapping("/save")
    public  ResponseEntity save(@RequestBody Inventory inventory){
        return inventoryService.saveInventory(inventory);
    }
    @GetMapping("/inventoryId/{inventoryId}")
    public Inventory findByInventoryId(@PathVariable("inventoryId") Integer inventoryId){
        return inventoryService.findById(inventoryId);
    }
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Inventory inventory){
        return inventoryService.update(inventory);
    }
    @DeleteMapping("/inventoryId/{inventoryId}")
    public ResponseEntity delete(@PathVariable("inventoryId") Integer inventoryId){
        return inventoryService.delete(inventoryId);
    }
}
