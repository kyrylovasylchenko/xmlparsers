package com.netcracker.repository;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.netcracker.model.ProjectDTO;
import com.netcracker.model.ProjectEntity;
import lombok.extern.log4j.Log4j;
import net.java.ao.DBParam;
import net.java.ao.Query;


import java.util.List;
@Log4j
public class ProjectRepositoryImpl implements ProjectRepository {

    private final ActiveObjects ao;

    public ProjectRepositoryImpl(ActiveObjects ao) {
        this.ao = ao;
    }

    @Override
    public ProjectEntity add(ProjectDTO project) {

            ProjectEntity projectEntity = ao.create(ProjectEntity.class,
                    new DBParam("PROJECT_NAME", project.getProjectName()));
            projectEntity.setBassProjectKey(project.getBassProjectKey());
            projectEntity.setScripts(project.getScripts().toString());
            projectEntity.setTargetProjectKey(project.getTargetProjectKey());
            projectEntity.save();
            return projectEntity;
    }

    @Override
    public ProjectEntity[] all() {
        return ao.find(ProjectEntity.class);
    }


    public boolean delete(String projectName){
        return false;
    }
}
