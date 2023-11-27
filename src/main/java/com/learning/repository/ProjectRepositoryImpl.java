package com.learning.repository;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.learning.model.DTO.ProjectDTO;
import com.learning.model.entity.ProjectEntity;
import lombok.extern.log4j.Log4j;
import net.java.ao.DBParam;
import net.java.ao.Query;

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

    @Override
    public ProjectEntity getByProjectName(String projectName) {
        ProjectEntity[] projectEntities = ao.find(ProjectEntity.class, Query.select().where("PROJECT_NAME = ?", projectName));
        return projectEntities[0];
    }

    @Override
    public void deleteProjectByName(String projectName) {
        try {
            ao.delete(getByProjectName(projectName));
        }catch (Exception e){
            log.error(e);
        }
    }


}
