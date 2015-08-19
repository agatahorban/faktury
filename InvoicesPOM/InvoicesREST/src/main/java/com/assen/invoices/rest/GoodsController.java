package com.assen.invoices.rest;

import com.assen.invoices.dto.GoodsListDto;
import com.assen.invoices.entities.Goods;
import com.assen.invoices.service.api.IGoodsService;
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
@Path("/goods")
@RolesAllowed({"ADMIN","PERM_GOODS"})
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
    
    @EJB
    private IGoodsService goodsService;
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllGoods() {
        List<Goods> allGoods = goodsService.findAllGoods();
        GoodsListDto goodsEntity = new GoodsListDto(allGoods);
        return Response.ok().entity(goodsEntity).build();
    }
}
