package com.assen.invoices.rest;

import com.assen.invoices.dto.ContractorListDto;
import com.assen.invoices.entities.Contractor;
import com.assen.invoices.service.api.IContractorService;
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

/**
 *
 * @author Agata
 */
@Stateless
@Path("/contractors")
@RolesAllowed({"ADMIN","PERM_CONTR"})
public class ContractorController {

    @EJB
    private IContractorService contractorService;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/all")
    public Response findAllContractors() {
        List<Contractor> allContractors = contractorService.findAllContractors();
        ContractorListDto entity = new ContractorListDto(allContractors);
        return Response.ok().entity(entity).build();
    }
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addContractor(Contractor contractor) {
        Contractor insertResult = contractorService.insertNewContractors(contractor);
        if (insertResult != null) {
            return Response.ok().entity(insertResult).build();
        } else {
            return Response.serverError().build();
        }
    }
    
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateContractor(Contractor contractor) {
        Contractor updateResult = contractorService.updateContractors(contractor);
        if (updateResult != null) {
            return Response.ok().entity(updateResult).build();
        } else {
            return Response.serverError().build();
        }
    }
    
     @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteContractors(ContractorListDto contractorsToDelete) {
        boolean deleteResult = contractorService.removeContractors(contractorsToDelete);
        if (deleteResult) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }
    
      @POST
    @Path("/findByCutName")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_XML)
    public Response filterContractorsByCutname(String cutName) {
        Contractor filterResult = contractorService.findByCutName(cutName);
        if (filterResult != null) {
            return Response.ok().entity(filterResult).build();
        } else {
            return Response.serverError().build();
        }
    }
}
