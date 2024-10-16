package com.example.demo.Feattures.Ingredient;

import java.util.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Domain.RepositoryIngredient;

import reactor.core.publisher.Flux;
import org.springframework.web.bind.annotation.GetMapping;


@Configuration
public class GetAllIngredient {
    
    public record Response(UUID id, String name, double price){}

    @RestController
    public class Controller{
        private final UseCase useCase;
        public Controller(final UseCase useCase){
            this.useCase = useCase;
        }
        @GetMapping("/ingredients")     
        Flux<Response> handle(){
            return useCase.handle();
        }
    }
    public interface  UseCase {    
       Flux<Response> handle(); 
    }
    @Component
    public class UseCaseImpl implements  UseCase{
        private final RepositoryIngredient repository;
        public UseCaseImpl(RepositoryIngredient repository){
            this.repository = repository;
        }
        @Override
        public Flux<Response> handle() {
           return repository.getAll()
            .map(i->new Response(i.getId(), i.getName(), i.getPrice()
           ));
        }

    }
}
