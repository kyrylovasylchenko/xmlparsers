package com.learning.rest;

import com.learning.services.CleaningTaskService;
import lombok.extern.log4j.Log4j;

import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/task")
@Log4j
public class CleaningTaskRest {

    private final CleaningTaskService cleaningTaskService;

    public CleaningTaskRest(CleaningTaskService cleaningTaskService) {
        this.cleaningTaskService = cleaningTaskService;
    }

    @PUT
    public Response cleanContent(@FormParam("projectName") String projectName, @FormParam("pageId") String pageId){
        String result = cleaningTaskService.cleaningContent(projectName, pageId);
        return Response.ok(result).build();
    }
}
