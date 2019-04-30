package com.example.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
public class Inventory implements Serializable {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String itemName;
    @JsonProperty
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
