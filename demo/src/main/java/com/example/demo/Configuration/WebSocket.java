package com.example.demo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

public class WebSocket {

}

@Configuration
class WebConfig {

    @Bean
    public HandlerMapping handlerMapping() {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/ws", new ExampleHandler());
        int order = -1; // before annotated controllers

        return new SimpleUrlHandlerMapping(map, order);
    }

    class ExampleHandler implements WebSocketHandler {

        @SuppressWarnings("null")
        @Override
        public Mono<Void> handle(WebSocketSession session) {
            Mono<Void> input = session.receive()
                    .doOnNext(message -> {
                        // ...
                    })
                    .then();

            Flux<String> source = Flux.just("Ok");
            Mono<Void> output = session.send(source.map(session::textMessage));

            return Mono.zip(input, output).then();
        }

    }
}
