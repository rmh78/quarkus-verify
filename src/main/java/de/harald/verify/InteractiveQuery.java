package de.harald.verify;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.errors.InvalidStateStoreException;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;

@ApplicationScoped
public class InteractiveQuery {

    @Inject
    KafkaStreams kafkaStreams;

    public List<String> getAll() {

        List<String> all = new ArrayList<>();
        KeyValueIterator<String, String> range = getStore().all();
        while (range.hasNext()) {
          KeyValue<String, String> next = range.next();
          all.add(next.value);
        }

        return all;
    }
    

    private ReadOnlyKeyValueStore<String, String> getStore() {
        while (true) {
            try {
                return kafkaStreams.store(TopologyProducer.STORE, QueryableStoreTypes.keyValueStore());
            } catch (InvalidStateStoreException e) {
                // ignore, store not ready yet
            }
        }
    }
}