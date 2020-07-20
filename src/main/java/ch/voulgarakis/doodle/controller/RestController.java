package ch.voulgarakis.doodle.controller;

import ch.voulgarakis.doodle.model.Poll;
import ch.voulgarakis.doodle.model.User;
import ch.voulgarakis.doodle.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //Maybe it should have been a request param as well, since we are dealing with names directly, which can be long (instead of userId)
    //Yet the point I am trying to make here is that there are 2 approaches we can use (compared with search by title)
    @GetMapping("/user/{name}")
    public Flux<Poll> listPollsForUser(@PathVariable String name) {
        User user = User.builder()
                .name(name)
                .build();
        return pollService.findForUser(user);
    }

    @GetMapping()
    public Mono<Poll> searchByTitle(@RequestParam String title) {
        return pollService.findForTitle(title);
    }

    @GetMapping("/date/{createdAfter}")
    public Flux<Poll> listPollsAfterDate(@PathVariable long createdAfter) {
        return pollService.findForCreatedAfter(createdAfter);
    }

}
