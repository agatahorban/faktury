package com.assen.invoices.rest;

import com.assen.invoices.dto.WarehouseListDto;
import com.assen.invoices.entities.Warehouse;
import com.assen.invoices.service.api.IWarehouseService;
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
@Path("/warehouses")
@RolesAllowed({"ADMIN", "PERM_WAREHOUSE"})
public class WarehouseController {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    @EJB
    private IWarehouseService warehouseService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllCollectivePackages() {
        List<Warehouse> warehouses = warehouseService.findAllWarehouses();
        WarehouseListDto warehouseListDto = new WarehouseListDto();
        warehouseListDto.setWarehouses(warehouses);
        return Response.ok().entity(warehouseListDto).build();
    }
}
