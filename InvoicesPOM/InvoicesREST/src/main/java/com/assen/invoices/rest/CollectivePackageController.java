package com.assen.invoices.rest;

import com.assen.invoices.dto.CollectivePackageListDto;
import com.assen.invoices.entities.CollectivePackage;
import com.assen.invoices.service.api.ICollectivePackageService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
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
@Path("/collectivePackages")
@RolesAllowed({"ADMIN","PERM_COLLECTIVE_PACKAGE"})
public class CollectivePackageController {

    private static final Logger logger = LoggerFactory.getLogger(CollectivePackageController.class);
    
    @EJB
    private ICollectivePackageService collectivePackageService;
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllCollectivePackages() {
        List<CollectivePackage> allCollectivePackages = collectivePackageService.findAllCollectivePackages();
        CollectivePackageListDto collectivePackages = new CollectivePackageListDto();
        collectivePackages.setPackages(allCollectivePackages);
        return Response.ok().entity(collectivePackages).build();
    }
}
