package com.pf.bogdan.pharmacy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan on 07.11.2017.
 */

public class Medicine implements Serializable{

    private Integer id;
    private String name;
    private String description;
    private String producer;
    private Double price;
    private String history;

    public Medicine() {
        price = 0.0;
        history="0.0";
    }

    public Medicine(Integer id, String name, String description, String producer, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.price = price;
        this.history = price.toString();
    }

    public void setData(Medicine medicine){
        name = medicine.getName();
        description = medicine.getDescription();
        producer = medicine.getProducer();
        price = medicine.getPrice();
        history=medicine.getPrice().toString();
    }

    public boolean empty(){
        return name==null && description==null && producer==null;
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
        if(!this.price.equals(price))
            history+=";"+price.toString();
        this.price = price;
    }

    public List<Double> allPrices() {
        List<Double> result = new ArrayList<>();
        String[] tokens = history.split(";");
        for(String token : tokens)
            result.add(Double.parseDouble(token));
        return result;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
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
