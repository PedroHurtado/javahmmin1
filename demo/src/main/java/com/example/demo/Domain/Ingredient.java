package com.example.demo.Domain;

import com.example.demo.Core.EntityBase;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Ingredient extends EntityBase {
    private String name;
    private Double price;
    protected Ingredient(UUID id, String name, Double price){
        super(id);
        this.name = name;
        this.price = price;
    }
    public void Update(String name, Double price){
        this.name =name;
        this.price =price;
    }
    public static Ingredient Create(String name, Double price){
        return new Ingredient(UUID.randomUUID(),name,price);
    }

}
