package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class EventConsumer {

    @Inject
    ReceivedMessages received;

    @Incoming("events-in")
    public void onMessage(String payload) {
        received.add(payload);
    }
}
