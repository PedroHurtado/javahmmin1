package com.example.demo.Infraestructure;

import java.util.List;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import com.example.demo.Domain.Pizza;
import com.example.demo.Domain.RepositoryPizza;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class RepositoryPizzaImpl implements RepositoryPizza {

    public static List<Pizza> pizzas = new ArrayList<>();
    @Override
    public Mono<Pizza> add(Pizza entity) {
        pizzas.add(entity);
        return Mono.just(entity);
        
    }
    @Override
    public Flux<Pizza> getAll() {
       return Flux.fromIterable(pizzas);
    }
    
}
