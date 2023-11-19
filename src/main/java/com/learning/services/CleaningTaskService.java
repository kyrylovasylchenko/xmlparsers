package com.learning.services;

import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.pages.PageManager;
import com.learning.model.ProjectDTO;
import com.learning.model.Task;
import com.learning.utils.ParseScripts;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
public class CleaningTaskService {
    private final ProjectService projectService;
    private final ParseScripts parseScripts;
    private final PageManager pageManager;

    public CleaningTaskService(ProjectService projectService, ParseScripts parseScripts, PageManager pageManager) {
        this.projectService = projectService;
        this.parseScripts = parseScripts;
        this.pageManager = pageManager;
    }

    public void cleaningContent(String projectName, String pageId){
        Task task = createTask(projectName);
        List<Page> descendants = pageManager.getDescendants(pageManager.getPage(Long.parseLong(pageId)));
        for(Page page : descendants){
            task.getScripts().forEach(script -> script.run(page.getBodyAsString()));
        }
    }

    private Task createTask(String projectName){
        ProjectDTO projectByName = projectService.getProjectByName(projectName);
        return new Task(projectByName, parseScripts.getScriptsForProject(projectByName));
    }


}
