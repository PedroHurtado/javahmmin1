package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;

import static org.springframework.http.MediaType.APPLICATION_NDJSON_VALUE;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
public class StreamingController {

    @GetMapping(path="/sse",produces = TEXT_EVENT_STREAM_VALUE)
    Flux<Data> sse() {
        return source();
    }

    @GetMapping(path="/ndjson",produces = APPLICATION_NDJSON_VALUE)
    Flux<Data> ndjson() {
        return source();
    }

    @GetMapping(path="/json")
    Flux<Data> json() {
        return source();
    }

    private Flux<Data> source() {
        return Flux.interval(Duration.ofSeconds(1))
                .take(5)
                .map(i -> new Data(i, Instant.now()));
    }

}