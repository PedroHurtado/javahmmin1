package com.example.demo.Feattures.Ingredient;

import java.util.UUID;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Core.NotFoundException;
import com.example.demo.Domain.RepositoryIngredient;


import reactor.core.publisher.Mono;


@Configuration
public class GetIngredient {
    public record Response(UUID id, String name, double Price) {
    }

    public class Controller {
        private final UseCase useCase;

        public Controller(final UseCase useCase) {
            this.useCase = useCase;
        }

        @GetMapping("/ingredients/{id}")
        Mono<Response> handle(@PathVariable() UUID id) {
            return useCase.handle(id);
        }
    }

    public interface UseCase {
        Mono<Response> handle(UUID id);
    }

    public class UseCaseImpl implements UseCase {

        private final RepositoryIngredient repository;

        public UseCaseImpl(final RepositoryIngredient repository) {
            this.repository = repository;
        }

        @Override
        public Mono<Response> handle(UUID id) {
            return repository.get(id)
                    .switchIfEmpty(Mono.error(new NotFoundException()))
                    .map(i->new Response(i.getId(), i.getName(), i.getPrice()));

        }
    }
}
