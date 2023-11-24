package com.learning.config;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.confluence.pages.PageManager;
import com.atlassian.confluence.user.UserAccessor;
import com.learning.model.scripts.ReplaceSpaceKey;
import com.learning.model.scripts.ReplaceUserKey;
import com.learning.repository.ProjectRepository;
import com.learning.repository.ProjectRepositoryImpl;
import com.learning.services.CleaningTaskService;
import com.learning.services.ProjectService;
import com.learning.utils.ParseScripts;
import org.springframework.context.annotation.Bean;



public class CustomBeans {
    @Bean
    public ProjectRepository projectRepository(ActiveObjects ao){
        return new ProjectRepositoryImpl(ao);
    }

    @Bean
    public ProjectService projectService(ProjectRepository pr){
        return new ProjectService(pr);
    }

    @Bean
    public CleaningTaskService cleaningTaskService(ProjectService ps, ParseScripts parseScripts, PageManager pageManager){
        return new CleaningTaskService(ps, parseScripts, pageManager);
    }
    @Bean
    public ParseScripts parseScripts(UserAccessor userAccessor){
        return new ParseScripts(userAccessor);
    }
}
