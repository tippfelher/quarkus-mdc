package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.MDC;

@ApplicationScoped
public class HandsomeService {

    @Inject
    EventProducer producer;

    public void test() {
        MDC.put("username", "admin");
        producer.emit("hello-kafka");
    }

}
