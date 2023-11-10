package com.netcracker.repository;

import com.atlassian.activeobjects.tx.Transactional;
import com.netcracker.model.ProjectDTO;
import com.netcracker.model.ProjectEntity;

import java.util.List;

@Transactional
public interface ProjectRepository {
    ProjectEntity add(ProjectDTO project);
    ProjectEntity[] all();

    ProjectEntity getByProjectName(String projectName);
}
