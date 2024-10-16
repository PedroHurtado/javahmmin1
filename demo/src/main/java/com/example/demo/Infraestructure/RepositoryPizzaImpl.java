package com.example.demo.Infraestructure;

import java.util.List;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import com.example.demo.Domain.Pizza;
import com.example.demo.Domain.RepositoryPizza;


@Component
public class RepositoryPizzaImpl implements RepositoryPizza {

    public static List<Pizza> pizzas = new ArrayList<>();
    @Override
    public void add(Pizza entity) {
        pizzas.add(entity);
        
    }
    
}
