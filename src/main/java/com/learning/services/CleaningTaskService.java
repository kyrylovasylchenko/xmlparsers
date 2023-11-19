package com.learning.services;

import com.learning.model.ProjectDTO;
import lombok.extern.log4j.Log4j;

@Log4j
public class CleaningTaskService {
    private final ProjectService projectService;

    public CleaningTaskService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void cleaningContent(String projectName){
        ProjectDTO projectByName = projectService.getProjectByName(projectName);
        log.error("GETTING PROJECT");
        log.error(projectByName);
    }



}
