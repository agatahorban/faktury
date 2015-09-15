package com.assen.invoices.rest;

import com.assen.invoices.dto.GoodsListDto;
import com.assen.invoices.entities.Goods;
import com.assen.invoices.service.api.IGoodsService;
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
@Path("/goods")
@RolesAllowed({"ADMIN", "PERM_GOODS"})
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

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addGoods(Goods goods) {
        Goods insertResult = goodsService.insertNewGoods(goods);
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
    public Response updateGoods(Goods goods) {
        Goods updateResult = goodsService.updateGoods(goods);
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
    public Response deleteGoods(GoodsListDto goodsToDelete) {
        boolean deleteResult = goodsService.removeGoods(goodsToDelete);
        if (deleteResult) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/findByIndex1")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_XML)
    public Response filterGoodsByIndex1(String index1) {
        Goods filterResult = goodsService.findGoodsByIndex1(index1);
        if (filterResult != null) {
            return Response.ok().entity(filterResult).build();
        } else {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/findByContractor")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_XML)
    public Response filterGoodsByContractor(String cutName) {
        List<Goods> filterResult = goodsService.findGoodsByContractor(cutName);
        GoodsListDto filteredEntities = new GoodsListDto(filterResult);
        return Response.ok().entity(filteredEntities).build();
    }

    @POST
    @Path("/findByGroup")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_XML)
    public Response filterGoodsByGroup(String name) {
        List<Goods> filterResult = goodsService.findGoodsByGroup(name);
        GoodsListDto filteredEntities = new GoodsListDto(filterResult);
        return Response.ok().entity(filteredEntities).build();
    }
}
