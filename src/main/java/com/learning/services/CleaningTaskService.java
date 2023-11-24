package com.learning.services;

import com.atlassian.confluence.core.DefaultSaveContext;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.pages.PageManager;
import com.learning.model.DTO.PageDTO;
import com.learning.model.PageUpdater;
import com.learning.model.DTO.ProjectDTO;
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
            PageDTO pageDTO = PageDTO.builder().page(page)
                    .bassKey(task.getProjectDTO().getBassProjectKey())
                    .targetKey(task.getProjectDTO().getTargetProjectKey())
                    .body(page.getBodyAsString())
                    .build();
            log.error(task.getScripts().size());
            task.getScripts().forEach(script -> script.run(pageDTO));
            if (pageDTO.isUpdated()){
                pageManager.saveNewVersion(pageDTO.getPage(), new PageUpdater(pageDTO.getBody()), DefaultSaveContext.SUPPRESS_NOTIFICATIONS);
            }
        }
    }

    private Task createTask(String projectName){
        ProjectDTO projectByName = projectService.getProjectByName(projectName);
        return new Task(projectByName, parseScripts.getScriptsForProject(projectByName));
    }


}
