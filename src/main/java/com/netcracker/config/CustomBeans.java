package com.netcracker.config;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.netcracker.repository.ProjectRepository;
import com.netcracker.repository.ProjectRepositoryImpl;
import com.netcracker.services.CleaningTaskService;
import com.netcracker.services.ProjectService;
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
