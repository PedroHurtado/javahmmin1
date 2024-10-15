package com.example.demo.Domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Collections;
import com.example.demo.Core.EntityBase;

import lombok.Getter;
import lombok.AccessLevel;

@Getter
public class Pizza extends EntityBase {
    private static final double PROFIT = 1.2;
    private String name;
    private String description;
    private String url;
    @Getter(AccessLevel.NONE)
    private Set<Ingredient> ingredients = new HashSet<>();

    protected Pizza(UUID id,String name,
            String description,
            String url) {
         super(id);       
         this.name = name;
         this.description = description;
         this.url=url;
    }
    public double getPrice(){
        return ingredients.stream().map(i->i.getPrice())
            .reduce(0.0, Double::sum) * PROFIT; 
        
    }
    public Set<Ingredient> getIngredients(){
        return Collections.unmodifiableSet(ingredients);
    }
    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }
    public void removeIngredient(Ingredient ingredient){
        ingredients.remove(ingredient);
    }
    public void update(String name,String description,String url){
        this.name = name;
        this.description = description;
        this.url=url;
    }
    public static Pizza create(String name,String description,String url){
        return new Pizza(UUID.randomUUID(), name, description, url);
    }
}
