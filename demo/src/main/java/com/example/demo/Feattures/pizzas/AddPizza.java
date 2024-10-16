package com.example.demo.Feattures.pizzas;

import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Core.NotFoundException;
import com.example.demo.Domain.Ingredient;
import com.example.demo.Domain.Pizza;
import com.example.demo.Domain.RepositoryIngredient;
import com.example.demo.Domain.RepositoryPizza;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

public class AddPizza {
    
    public record RequestIngredient(UUID id){}

    public record Request(
            String name,
            String description,
            String url,
            Flux<RequestIngredient> ingredients) {
    }

    public record ResponseIngredient(UUID id, String name) {

    }

    public record Response(
            UUID id,
            String name,
            String description,
            String url,
            double price,
            Flux<ResponseIngredient> ingredients) {
    }

    @RestController
    public class Controller {
        private final UseCase useCase;

        public Controller(final UseCase useCase) {
            this.useCase = useCase;
        }

        @PostMapping("/pizzas")
        public Mono<Response> postMethodName(@RequestBody Mono<Request> request) {
            return useCase.handle(request);
        }

    }

    public interface UseCase {
        public Mono<Response> handle(Mono<Request> request);
    }

    @Component
    public class UneCaseImpl implements UseCase {
        private final RepositoryPizza repository;
        private final RepositoryIngredient respositoryIngredient;

        public UneCaseImpl(
            final RepositoryPizza repository,
            final RepositoryIngredient respositoryIngredient
        ) {
            this.repository = repository;
            this.respositoryIngredient = respositoryIngredient;
        }

        @Override
        public Mono<Response> handle(Mono<Request> request) {

            return request.flatMap(p -> {
                
                Pizza pizza = Pizza.create(p.name(), p.description(), p.url());

                return p.ingredients.flatMap(UUID -> {
                    return this.respositoryIngredient.get(UUID.id())
                            .switchIfEmpty(Mono.error(new NotFoundException()));
                })
                .doOnNext(i -> pizza.addIngredient(i))
                .then(repository.add(pizza))              
                .map(savedPizza -> {                        
                        return new Response(
                                    savedPizza.getId(),
                                    savedPizza.getName(),
                                    savedPizza.getDescription(),
                                    savedPizza.getUrl(),
                                    savedPizza.getPrice(),
                                    generateIngredient(savedPizza.getIngredients()));
                });

            });
        }

        public Flux<ResponseIngredient> generateIngredient(Set<Ingredient> ingredients){
            return Flux.fromIterable(ingredients)
                .map(i->new ResponseIngredient(i.getId(), i.getName()));
        }
    }

}
