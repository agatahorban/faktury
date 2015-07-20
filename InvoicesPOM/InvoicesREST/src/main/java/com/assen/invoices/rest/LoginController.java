package com.assen.invoices.rest;

import com.assen.invoices.dto.LoginCredentialsDto;
import com.assen.invoices.entities.User;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Arek
 */
@Stateless
@Path("/login")
public class LoginController {

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response logIn(LoginCredentialsDto userData) {
        User user = new User();
        user.setLogin(userData.getLogin());
        user.setPassword(userData.getPassword());
        
        return Response.ok().entity(user).build();
    }
}
