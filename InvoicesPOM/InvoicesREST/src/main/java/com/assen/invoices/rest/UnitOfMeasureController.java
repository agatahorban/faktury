package com.assen.invoices.rest;

import com.assen.invoices.dto.UnitOfMeasureListDto;
import com.assen.invoices.entities.UnitOfMeasure;
import com.assen.invoices.service.api.IUnitOfMeasureService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("/unitsOfMeasure")
@RolesAllowed({"ADMIN","PERM_UNITS_MEAS"})
public class UnitOfMeasureController {

    private static final Logger logger = LoggerFactory.getLogger(UnitOfMeasureController.class);
    
    @EJB
    private IUnitOfMeasureService unitOfMeasureService;
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllCollectivePackages() {
        List<UnitOfMeasure> allCollectivePackages = unitOfMeasureService.findAllUnitsOfMeasure();
        UnitOfMeasureListDto collectivePackages = new UnitOfMeasureListDto();
        collectivePackages.setUnits(allCollectivePackages);
        return Response.ok().entity(collectivePackages).build();
    }
    
    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteGoods(UnitOfMeasureListDto unitOfMeasureToDelete) {
        boolean deleteResult = unitOfMeasureService.removeUnit(unitOfMeasureToDelete);
        if (deleteResult) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }
}
