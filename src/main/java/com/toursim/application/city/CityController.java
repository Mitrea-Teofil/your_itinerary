package com.toursim.application.city;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;

@Path("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;


    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello from Spring";
    }

//    @POST
//    @Consumes({ MediaType.APPLICATION_JSON })
//    public Response addCity(
//            City com.toursim.application.city, @Context UriInfo uriInfo) {
//        City saved = cityRepository.save(com.toursim.application.city);
//        return Response.status(Response.Status.CREATED.getStatusCode()).entity(saved).build();
//    }
}
