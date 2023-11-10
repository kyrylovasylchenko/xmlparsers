package com.netcracker.rest;



import com.google.gson.Gson;
import com.netcracker.model.ProjectDTO;
import com.netcracker.repository.ProjectRepositoryImpl;
import com.netcracker.services.ProjectService;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;

import javax.ws.rs.core.Response;


/**
 * A resource of message.
 */
@Path("/project")
@Log4j
public class MyRestResource {


    private final Gson gson = new Gson();
    private final ProjectRepositoryImpl projectRepository;
    private final ProjectService projectService;

    public MyRestResource(ProjectRepositoryImpl projectService, ProjectService projectService1) {
        this.projectRepository = projectService;
        this.projectService = projectService1;
    }


    @PUT
    public Response saveProject(@RequestBody ProjectDTO projectDTO)
    {
        projectRepository.add(projectDTO);
        return Response.ok(projectDTO.toString()).build();
    }

    @GET
    public Response getProjects(){
        log.error("HERE CHECK JSON 3");
        String json = gson.toJson(projectService.getAllProjects());
        log.error(json);
        return Response.ok(json).build();
    }


}