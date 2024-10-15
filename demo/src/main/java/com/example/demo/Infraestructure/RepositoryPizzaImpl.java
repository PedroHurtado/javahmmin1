package com.example.demo.Infraestructure;

import java.util.List;
import java.util.ArrayList;
import com.example.demo.Domain.Pizza;
import com.example.demo.Domain.RepositoryPizza;

import reactor.core.publisher.Mono;

public class RepositoryPizzaImpl implements RepositoryPizza {

    public static List<Pizza> pizzas = new ArrayList<>();
    @Override
    public Mono<Pizza> add(Pizza entity) {
        pizzas.add(entity);
        return Mono.just(entity);
    }
    
}
