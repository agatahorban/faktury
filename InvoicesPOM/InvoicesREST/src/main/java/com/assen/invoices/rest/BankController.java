/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.rest;

import com.assen.invoices.dto.BankListDto;
import com.assen.invoices.dto.ContractorListDto;
import com.assen.invoices.entities.Bank;
import com.assen.invoices.service.api.IBankService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author horbana
 */
@Stateless
@Path("/banks")
@RolesAllowed({"ADMIN", "PERM_BANK"})
public class BankController {
    @EJB
    private IBankService bankService;
    
     @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/all")
    public Response findAllBanks() {
        List<Bank> allBanks = bankService.findAllBanks();
        BankListDto entity = new BankListDto(allBanks);
        return Response.ok().entity(entity).build();
    }
}
