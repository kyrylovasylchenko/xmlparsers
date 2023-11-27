package com.learning.rest;



import com.google.gson.Gson;
import com.learning.model.DTO.ProjectDTO;
import com.learning.services.ProjectService;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.*;

import javax.ws.rs.core.Response;


/**
 * A resource of message.
 */
@Path("/project")
@Log4j
public class ProjectRest {

    private final Gson gson = new Gson();
    private final ProjectService projectService;

    public ProjectRest(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PUT
    public Response saveProject(@RequestBody ProjectDTO projectDTO)
    {
        projectService.add(projectDTO);
        return Response.ok(projectDTO.toString()).build();
    }

    @GET
    public Response getProjects(){
        String json = gson.toJson(projectService.getAllProjects());
        return Response.ok(json).build();
    }


    @DELETE
    public Response deleteProject(@FormParam("projectName") String projectName){
        projectService.deleteProjectByName(projectName);
        return Response.ok().build();
    }


}