package com.example.repository;

import com.example.domain.EInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<EInventory,Integer> {

}
