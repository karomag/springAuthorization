package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    private final GenericContainer<?> devapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private final GenericContainer<?> prodapp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeEach
    void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void contextDevLoads() {
        Integer devPort = devapp.getMappedPort(8080);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devPort + "/authorize?user=Igor&password=123", String.class);
        assertEquals("[\"READ\",\"WRITE\"]", forEntity.getBody());
    }

    @Test
    void contextProdLoads() {
        Integer prodPort = prodapp.getMappedPort(8081);
        ResponseEntity<String> forEntityProd = restTemplate.getForEntity("http://localhost:" + prodPort + "/authorize?user=Igor&password=123", String.class);
        assertEquals("[\"READ\",\"WRITE\"]", forEntityProd.getBody());
    }

}
