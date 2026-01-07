package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class EventProducer {

    @Inject
    @Channel("events-out")
    Emitter<String> emitter;

    public void emit(String payload) {
        emitter.send(payload);
    }
}
