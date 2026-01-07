package org.acme;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@QuarkusTestResource(KafkaResource.class)
class KafkaEmitterTest {

    @Inject
    EventProducer producer;

    @Inject
    ReceivedMessages received;

    @BeforeEach
    void setup() {
        received.clear();
    }

    @Test
    void emitter_sendet_nach_kafka_und_consumer_empfaengt() {
        MDC.put("username", "admin");
        producer.emit("hello-kafka");
        assertEquals("admin", MDC.get("username"));

        await().atMost(Duration.ofSeconds(10)).untilAsserted(() -> {
            assertTrue(received.snapshot().contains("hello-kafka"));
            assertEquals("admin", MDC.get("username"));
        });
    }
}
