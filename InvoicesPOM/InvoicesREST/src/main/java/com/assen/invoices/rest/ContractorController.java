/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.rest;

import com.assen.invoices.dto.ContractorListDto;
import com.assen.invoices.entities.Contractor;
import com.assen.invoices.service.api.IContractorService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
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
}
