package com.example.demo.Domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RepositoryPizza {
    Mono<Pizza> add(Pizza entity);
    Flux<Pizza> getAll();
}
