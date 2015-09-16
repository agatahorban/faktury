/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.rest;

import com.assen.invoices.dto.PaymentDateListDto;
import com.assen.invoices.entities.PaymentDate;
import com.assen.invoices.service.api.IPaymentDateService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author horbana
 */
@Stateless
@Path("/paymentDates")
@RolesAllowed({"ADMIN", "PERM_PAYMENT_DATE"})
public class PaymentDateController {

    @EJB
    private IPaymentDateService paymentDateService;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/all")
    public Response findAllBanks() {
        List<PaymentDate> allBanks = paymentDateService.findAllPaymentDates();
        PaymentDateListDto entity = new PaymentDateListDto(allBanks);
        return Response.ok().entity(entity).build();
    }
}
