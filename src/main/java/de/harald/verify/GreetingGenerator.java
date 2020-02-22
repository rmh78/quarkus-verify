package de.harald.verify;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;

@ApplicationScoped 
public class GreetingGenerator {

    @Outgoing("greetings")                        
    public Flowable<KafkaMessage<String, String>> generate() {           
        return Flowable.interval(1, TimeUnit.SECONDS)
                .map(tick -> KafkaMessage.of("hello", "world"));
    }
    
}