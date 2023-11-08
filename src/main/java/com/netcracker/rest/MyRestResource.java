package com.netcracker.rest;



import com.atlassian.confluence.pages.PageManager;
import com.netcracker.model.Project;
import org.springframework.web.bind.annotation.RequestBody;



import javax.ws.rs.*;

import javax.ws.rs.core.Response;


/**
 * A resource of message.
 */
@Path("/project")
public class MyRestResource {

    private final PageManager pageManager;


    public MyRestResource(PageManager pageManager) {
        this.pageManager = pageManager;

    }


    @PUT
    public Response saveProject(@RequestBody Project project)
    {
        return Response.ok(project.toString()).build();
    }


}