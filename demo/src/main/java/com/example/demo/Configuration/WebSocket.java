package com.example.demo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

//import reactor.core.publisher.Flux;
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
            return session.receive()
                    .concatMap(message -> {
                        Date now = new Date(); // Obtener la fecha actual
                        String payload = message.getPayloadAsText();
                        long start = Long.valueOf(payload);
                        System.out.println("Mensaje recibido: " + payload);                        
                        WebSocketMessage response = session.textMessage(Long.toString(now.getTime()-start));
                        return session.send(Mono.just(response));
                    })
                    .then(); // Completa cuando no hay más mensajes entrantes
        }

    }
}
