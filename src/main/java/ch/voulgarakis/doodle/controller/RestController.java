package ch.voulgarakis.doodle.controller;

import ch.voulgarakis.doodle.model.Poll;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("poll")
public class RestController {

    @GetMapping("/user/{userId}")
    public Flux<Poll> listPollsForUser(@PathVariable String userId) {
        return Flux.empty();
    }

    @GetMapping("/title/{title}")
    public Mono<Poll> searchByTitle(@PathVariable String title) {
        return Mono.empty();
    }

    @GetMapping("/date/{instant}")
    public Flux<Poll> listPollsAfterDate(@PathVariable Instant instant) {
        return Flux.empty();
    }


}
