package com.example.demo.Feattures.pizzas;

import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Domain.RepositoryPizza;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public class AddPizza {
    public record Request(
        String name, 
        String description,
        String url,
        Flux<UUID> ingredients
    ) {
    }

    public record ResponseIngredient() {
    }
    public record Response(
        UUID id,
        String name, 
        String description,
        String url,
        Flux<ResponseIngredient> ingredients
    ) {
    }

    @RestController
    public class Controller{
       private final UseCase useCase;
       public Controller(final UseCase useCase){
         this.useCase = useCase;
       }
       @PostMapping("/pizzas")
       public Mono<Response> postMethodName(@RequestBody Mono<Request> request) {           
        return useCase.handle(request);           
       }
        
       
    }
    public interface UseCase{
        public Mono<Response> handle(Mono<Request> request);
    }
    public class UneCaseImpl implements UseCase{
        private final RepositoryPizza repository;
        public UneCaseImpl(final RepositoryPizza repository){
            this.repository = repository;
        }
        @Override
        public Mono<Response> handle(Mono<Request> request) {            
            throw new UnsupportedOperationException("Unimplemented method 'handle'");
        }

    }
}
