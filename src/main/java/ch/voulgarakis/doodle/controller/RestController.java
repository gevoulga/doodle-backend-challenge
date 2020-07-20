package ch.voulgarakis.doodle.controller;

import ch.voulgarakis.doodle.model.Poll;
import ch.voulgarakis.doodle.model.User;
import ch.voulgarakis.doodle.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("poll")
public class RestController {

    private final PollService pollService;

    @Autowired
    public RestController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping("/user/{name}")
    public Flux<Poll> listPollsForUser(@PathVariable String name) {
        User user = User.builder()
                .name(name)
                .build();
        return pollService.findForUser(user);
    }

    @GetMapping("/title/{title}")
    public Mono<Poll> searchByTitle(@PathVariable String title) {
        return pollService.findForTitle(title);
    }

    @GetMapping("/date/{createdAfter}")
    public Flux<Poll> listPollsAfterDate(@PathVariable long createdAfter) {
        return pollService.findForCreatedAfter(createdAfter);
    }

}
