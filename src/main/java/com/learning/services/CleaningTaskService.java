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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public String cleaningContent(String projectName, String pageId){
        Task task = createTask(projectName);
        Page parentPage = pageManager.getPage(Long.parseLong(pageId));
        List<Page> descendants = pageManager.getDescendants(parentPage);
        descendants.add(parentPage);
        for(Page page : descendants){

            PageDTO pageDTO = PageDTO.builder().page(page)
                    .bassKey(task.getProjectDTO().getBassProjectKey())
                    .targetKey(task.getProjectDTO().getTargetProjectKey())
                    .body(page.getBodyAsString())
                    .scriptLogs(new HashMap<>())
                    .build();

            task.getScripts().forEach(script -> script.run(pageDTO));

            for (Map.Entry<String, String> entry : pageDTO.getScriptLogs().entrySet()) {
                String key = entry.getKey();
                String newValue = entry.getValue();

                List<String> existingList = task.getResults().computeIfAbsent(key, k -> new ArrayList<>());

                existingList.add(newValue);
            }


            if (pageDTO.isUpdated()){
                pageManager.saveNewVersion(pageDTO.getPage(), new PageUpdater(pageDTO.getBody()), DefaultSaveContext.SUPPRESS_NOTIFICATIONS);
            }
        }

        StringBuilder resultHtml = new StringBuilder();

        for (Map.Entry<String, List<String>> entry : task.getResults().entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();

            resultHtml.append(key).append("\n");

            for (String value : values) {
                resultHtml.append(value).append("\n");
            }
            resultHtml.append("\n");
        }

        return resultHtml.toString();
    }

    private Task createTask(String projectName){
        ProjectDTO projectByName = projectService.getProjectByName(projectName);
        return new Task(projectByName, parseScripts.getScriptsForProject(projectByName), new HashMap<>());
    }


}
