package com.learning.config;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.learning.repository.ProjectRepository;
import com.learning.repository.ProjectRepositoryImpl;
import com.learning.services.CleaningTaskService;
import com.learning.services.ProjectService;
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
    public CleaningTaskService cleaningTaskService(ProjectService ps){
        return new CleaningTaskService(ps);
    }
}
