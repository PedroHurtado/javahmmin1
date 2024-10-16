package com.example.demo.Infraestructure;

import com.example.demo.Domain.Ingredient;
import com.example.demo.Domain.RepositoryIngredient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class RepositoryIngredientImpl implements RepositoryIngredient{

    public static List<Ingredient> ingredients= new ArrayList<>();
    @Override
    public void add(Ingredient entity) {
        ingredients.add(entity);
    }
    @Override    
    public Mono<Ingredient> get(UUID id) {        
       var result =  ingredients.stream().filter(i->i.getId().equals(id)).findFirst();       
       if(!result.isPresent()) {
        return Mono.empty();
       }
       return Mono.just(result.get());
    }
    @Override
    public Flux<Ingredient> getAll() {
        return Flux.fromIterable(ingredients);
    }
    
    
}
