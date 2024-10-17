package com.example.demo.Infraestructure;

import com.example.demo.Domain.Ingredient;
import com.example.demo.Domain.RepositoryIngredient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

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

       WebClient client = WebClient.create("https://super-chainsaw-vp7g5r67w6hxv5j-3000.app.github.dev");

       return  client.get()
		.uri("/ingredients/{id}", id)
        .accept(MediaType.APPLICATION_JSON)        
		.retrieve()        
		.bodyToMono(Ingredient.class);

       /*var result =  ingredients.stream().filter(i->i.getId().equals(id)).findFirst();       
       if(!result.isPresent()) {
        return Mono.empty();
       }
       return Mono.just(result.get());*/
    }
    @Override
    public Flux<Ingredient> getAll() {
        return Flux.fromIterable(ingredients);
    }
    
    
}
