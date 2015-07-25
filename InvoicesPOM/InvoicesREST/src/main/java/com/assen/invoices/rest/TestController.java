package com.assen.invoices.rest;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Arek
 */
@Path("/test")
@Stateless
@RolesAllowed("ADMIN")
public class TestController {
    
    @GET
    @Path("/{param}")
//    @Produces(MediaType.APPLICATION_XML)
    public Response getMsg(@PathParam("param") String msg) {
        String output = "Test " + msg;
        return Response.status(200).entity(output).build();
    }
}
