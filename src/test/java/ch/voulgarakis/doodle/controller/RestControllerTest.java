package ch.voulgarakis.doodle.controller;

import ch.voulgarakis.doodle.model.Poll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestControllerTest {

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
                .hasSize(0);
    }

    @Test
    public void searchByTitle() {
    }

    @Test
    public void listPollsAfterDate() {
    }
}