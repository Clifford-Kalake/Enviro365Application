package com.enviro.assessment.grad001.CliffordKalake.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EnvironmentalData {
    @Id//  Marks this field as the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Specifies auto-increment ID generation supported by the database
    private Long id;

    private String data;// A field to store any kind of data

    public Long getId() {
        return id;// Standard getter for the 'id' field
    }

    public void setId(Long id) {
        this.id = id;// Standard setter for the 'id' field
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
