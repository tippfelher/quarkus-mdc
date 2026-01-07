package org.acme;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@ApplicationScoped
public class ReceivedMessages {

    private final Queue<String> messages = new ConcurrentLinkedQueue<>();

    public void add(String msg) {
        messages.add(msg);
    }

    public List<String> snapshot() {
        return new ArrayList<>(messages);
    }

    public void clear() {
        messages.clear();
    }
}
