package com.example.demo.Infraestructure;

import com.example.demo.Domain.Ingredient;
import com.example.demo.Domain.RepositoryIngredient;
import java.util.List;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RepositoryIngredientImpl implements RepositoryIngredient{

    public static List<Ingredient> ingredients= new ArrayList<>();
    @Override
    public void add(Ingredient entity) {
        ingredients.add(entity);
    }
    
    
}
