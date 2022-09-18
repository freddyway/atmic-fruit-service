package com.redhat.atomic.fruit;

import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

@Path("/fruit")
public class FruitResource {
    private final Logger logger = Logger.getLogger(FruitResource.class);

    @ConfigProperty(name = "hello.message")
    private String message;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        logger.debug("Hello metodo ha sido llamado!!!");
        return message;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> allFruits() {
        return Fruit.listAll();
    }

    @GET
    @Path("{season}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> fruitsBySeason(@PathParam("season") String season) {
        return Fruit.getAllFruitsForSeason(season);
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveFruit(Fruit fruit) {
        fruit.persist();
        final URI creaUri = UriBuilder.fromResource(FruitResource.class)
                .path(Long.toString(fruit.id))
                .build();

        return Response.created(creaUri).build();
    }
}