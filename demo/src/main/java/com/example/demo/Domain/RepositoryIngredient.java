package com.example.demo.Domain;

import reactor.core.publisher.Mono;
import java.util.UUID;
public interface RepositoryIngredient {
   void add(Ingredient entity);  
   Mono<Ingredient> get(UUID id);
} 
