package com.netcracker.rest;



import com.atlassian.confluence.pages.PageManager;
import com.google.gson.Gson;
import com.netcracker.model.Project;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.RequestBody;



import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


/**
 * A resource of message.
 */
@Path("/project")
@Log4j
public class MyRestResource {

    private final PageManager pageManager;
    private final Gson gson = new Gson();
    private final List<Project> projectList = new ArrayList<>();



    public MyRestResource(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @PUT
    public Response saveProject(@RequestBody Project project)
    {
        projectList.add(project);
        return Response.ok(project.toString()).build();
    }

    @GET
    public Response getProjects(){

        return Response.ok(projectList.toString()).build();
    }


}