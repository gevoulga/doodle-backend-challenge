package ch.voulgarakis.doodle.service;

import ch.voulgarakis.doodle.model.Poll;
import ch.voulgarakis.doodle.model.User;
import ch.voulgarakis.doodle.repository.PollRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class PollService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PollService.class);

    private final PollRepository pollRepository;

    @Autowired
    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Mono<Poll> findForTitle(String title) {
        LOGGER.info("Searching for poll with title: {}", title);
        return pollRepository.findByTitle(title);
    }

    public Flux<Poll> findForUser(User user) {
        LOGGER.info("Searching for polls created by user: {}", user);
        return pollRepository.findByInitiator(user);
    }

    public Flux<Poll> findForCreatedAfter(long createdAfter) {
        LOGGER.info("Searching for polls created after: {}", createdAfter);
        return pollRepository.findByInitiatedGreaterThanEqual(createdAfter);
    }
}
