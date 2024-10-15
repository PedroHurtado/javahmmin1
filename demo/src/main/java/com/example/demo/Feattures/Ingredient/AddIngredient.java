package com.example.demo.Feattures.Ingredient;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Domain.Ingredient;
import com.example.demo.Domain.RepositoryIngredient;

import reactor.core.publisher.Mono;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Configuration
public class AddIngredient {
    public record Request(String name, Double price) {
    }
    public record Response(UUID id,String name, Double price) {
    }

    @RestController
    public class Controller{
        private final UseCase useCase;        
        public Controller(final UseCase useCase){
            this.useCase = useCase;
        }        
        @PostMapping("/ingredients")
        @ResponseStatus(HttpStatus.CREATED)
        public Mono<Response> handler(@RequestBody() Mono<Request> request){                                    
            return useCase.handler(request);
        }
    }
    public interface UseCase{
        Mono<Response> handler(Mono<Request> request);
    }
    @Component
    public class UseCaseImpl implements UseCase{

        private final RepositoryIngredient repository;
        public UseCaseImpl(final RepositoryIngredient repository){
            this.repository = repository;
        }
        @Override
        public Mono<Response> handler(Mono<Request> request) {            
            return request.map(i->{                                                
                Ingredient ingredient = Ingredient.create(i.name(), i.price());
                this.repository.add(ingredient); 
                return new Response(
                    ingredient.getId(), 
                    ingredient.getName(), 
                    ingredient.getPrice());               
            });
            
        }

    }

}
