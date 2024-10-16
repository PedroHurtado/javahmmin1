package com.example.demo.Configuration;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.example.demo.Domain.Pizza;
import com.example.demo.Domain.RepositoryPizza;

import reactor.core.publisher.Mono;


@Configuration
public class Routes {
    @Bean
    public RouterFunction<ServerResponse> productRoutes(PizzaHandler handler) {        
        return route(GET("/ingredients")
                .and(accept(APPLICATION_JSON)), handler::getAll);   
    }

    @Component
    public class PizzaHandler {
        private final RepositoryPizza repository;
        public PizzaHandler(RepositoryPizza repository){
            this.repository = repository;
        }
        public Mono<ServerResponse> getAll(ServerRequest request) {                        
            return ok().body(repository.getAll(), Pizza.class);
        }
    }
}
