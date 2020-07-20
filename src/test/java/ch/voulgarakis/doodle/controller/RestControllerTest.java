package ch.voulgarakis.doodle.controller;

import ch.voulgarakis.doodle.model.Poll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webClient;

    @Test
    public void listPollsForUser() {
        webClient.get()
                .uri("/poll/user/John Doe")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(Poll.class)
                .hasSize(36);
    }

    @Test
    public void searchByTitle() {
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/poll")
                        .queryParam("title", "Why did the chicken cross the road?")
                        .build())
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(Poll.class)
                .hasSize(1)
                .consumeWith(res -> LOGGER.info("Poll with Title: {}", res.getResponseBody()));
    }

    @Test
    public void listPollsAfterDate() {
        webClient.get()
                .uri("/poll/date/1485388243056")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(Poll.class)
                .hasSize(4)
                .consumeWith(res -> LOGGER.info("Polls created after: {}", res.getResponseBody()));
    }
}