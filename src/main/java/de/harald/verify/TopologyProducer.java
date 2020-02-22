package de.harald.verify;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;

@ApplicationScoped
public class TopologyProducer {

    public final static String TOPIC = "greetings";
    public final static String STORE = "greetings-store";

    @Produces
    public Topology buildTopology() {
        KeyValueBytesStoreSupplier storeSupplier = Stores.persistentKeyValueStore(STORE);

        StreamsBuilder builder = new StreamsBuilder();
        builder.table("greetings", Materialized.<String, String>as(storeSupplier)
            .withKeySerde(Serdes.String())
            .withValueSerde(Serdes.String()));

        return builder.build();
    }
    
}