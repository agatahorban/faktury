package com.assen.invoices.rest;

import com.assen.invoices.dto.VATRateListDto;
import com.assen.invoices.entities.VATRate;
import com.assen.invoices.service.api.IVATRateService;
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
@Path("/vatRates")
@RolesAllowed({"ADMIN","PERM_VAT_RATE"})
public class VATRateController {

    private static final Logger logger = LoggerFactory.getLogger(VATRateController.class);
    
    @EJB
    private IVATRateService vatRateService;
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllCollectivePackages() {
        List<VATRate> allVatRates = vatRateService.findAllVATRates();
        VATRateListDto vatRates = new VATRateListDto();
        vatRates.setVatRates(allVatRates);
        return Response.ok().entity(vatRates).build();
    }
}
