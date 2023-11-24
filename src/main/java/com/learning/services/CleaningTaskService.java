package com.learning.services;

import com.atlassian.confluence.core.DefaultSaveContext;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.pages.PageManager;
import com.learning.model.PageDTO;
import com.learning.model.PageUpdater;
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
        log.error("WE ARE HERE 1");
        Task task = createTask(projectName);
        List<Page> descendants = pageManager.getDescendants(pageManager.getPage(Long.parseLong(pageId)));
        log.error("WE ARE HERE 2");
        for(Page page : descendants){
            log.error("WE ARE HERE 3");
            PageDTO pageDTO = PageDTO.builder().page(page)
                    .bassKey(task.getProjectDTO().getBassProjectKey())
                    .targetKey(task.getProjectDTO().getTargetProjectKey())
                    .body(page.getBodyAsString())
                    .build();
            log.error("WE ARE HERE 4");
            log.error(task.getScripts().size());
            task.getScripts().forEach(script -> script.run(pageDTO));
            log.error("WE ARE HERE 5");
            if (pageDTO.isUpdated()){
                log.error("WE ARE HERE 6");
                pageManager.saveNewVersion(pageDTO.getPage(), new PageUpdater(pageDTO.getBody()), DefaultSaveContext.SUPPRESS_NOTIFICATIONS);
            }
        }
    }

    private Task createTask(String projectName){
        ProjectDTO projectByName = projectService.getProjectByName(projectName);
        return new Task(projectByName, parseScripts.getScriptsForProject(projectByName));
    }


}
