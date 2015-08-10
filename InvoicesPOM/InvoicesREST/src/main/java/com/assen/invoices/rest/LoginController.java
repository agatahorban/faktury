package com.assen.invoices.rest;

import com.assen.invoices.dto.LoginCredentialsDto;
import com.assen.invoices.entities.User;
import com.assen.invoices.service.api.ILoginService;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Arek
 */
@Stateless
@Path("/login")
@PermitAll
public class LoginController {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @EJB
    private ILoginService loginService;

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response logIn(LoginCredentialsDto userData) {
        User user = loginService.findUserByLoginAndPassword(userData);
        
        if(user == null) {
            logger.warn("Wrong credentials provided with login: " + userData.getLogin());
            return Response.serverError().build();
        }
        logger.info("Successfully logging user: " + userData.getLogin());
        return Response.ok().entity(user).build();
    }
}
