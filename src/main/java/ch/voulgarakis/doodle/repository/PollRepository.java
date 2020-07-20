package ch.voulgarakis.doodle.repository;

import ch.voulgarakis.doodle.model.Poll;
import ch.voulgarakis.doodle.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring data creating the queries.
 * Spring data will do the underlying magic to query the mongodb
 * <p>
 * Some useful links:
 * https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/mongo.repositories.html
 * https://www.baeldung.com/queries-in-spring-data-mongodb
 * <p>
 * https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 */
@Repository
public interface PollRepository extends ReactiveMongoRepository<Poll, String> {

    //Find the poll by the title
    Mono<Poll> findByTitle(String title);

    //Find the polls by the user having created the poll
    Flux<Poll> findByInitiator(User initiator);

    //Find the the polls
    @Query("{'initiated': {$gte: ?0}}")
    Flux<Poll> findByInitiatedGreaterThanEqual(long initiated);


    ///////////////////////////
    ///Future improvements/////
    ///////////////////////////
    //Paging and Sorting
    //Page<User> findByTitle(String title, Pageable pageable);
    Flux<User> findByTitle(String title, Pageable pageable);

    Flux<User> findByTitle(String title, Sort sort);

}
