package de.harald.verify;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @Inject
    EntityManager em;

    @Inject
    InteractiveQuery iq;

    @GET
    @Path("/db")
    public Greeting hello() {
        // fetch data from postgres database
        return em.find(Greeting.class, 1l);
    }

    @GET
    @Path("/kafka")
    public List<String> getAll() {
        // fetch data from kafka-streams interactive query
        return iq.getAll();
    }
}