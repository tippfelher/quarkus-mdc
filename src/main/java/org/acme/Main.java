package org.acme;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.MDC;

@QuarkusMain
public class Main implements QuarkusApplication {

    @Inject
    @Channel("bla")
    Emitter<String> emitter;

    public static void main(String[] args) {
        Quarkus.run(Main.class, args);
    }

    @Override
    public int run(String... args) throws Exception {
        MDC.put("username", "admin");
        emitter.send("Hello World");
        System.out.println(MDC.get("username"));
        return 0;
    }
}
