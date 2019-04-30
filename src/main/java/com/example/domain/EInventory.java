package com.example.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "INVENTORY")
public class EInventory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;
    @Column(name="ITEM_NAME")
    private String itemName;
    @Column(name="CATEGORY")
    private String category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
