package com.assen.invoices.rest;

import com.assen.invoices.dto.GroupListDto;
import com.assen.invoices.entities.Group;
import com.assen.invoices.service.api.IGroupService;
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
@Path("/groups")
@RolesAllowed({"ADMIN","PERM_GROUP"})
public class GroupController {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
    
    @EJB
    private IGroupService groupService;
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllGroups() {
        List<Group> allGroups = groupService.findAllGoods();
        GroupListDto groupsDto = new GroupListDto();
        groupsDto.setGroups(allGroups);
        return Response.ok().entity(groupsDto).build();
    }
}
