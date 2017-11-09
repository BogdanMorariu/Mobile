package com.pf.bogdan.pharmacy.model;

import java.io.Serializable;

/**
 * Created by Bogdan on 07.11.2017.
 */

public class Medicine implements Serializable{
    Integer id;
    String name;
    String description;
    String producer;
    Double price;

    public Medicine() {
    }

    public Medicine(Integer id, String name, String description, String producer, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", producer='" + producer + '\'' +
                ", price=" + price +
                '}';
    }
}
