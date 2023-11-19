package com.learning.repository;

import com.atlassian.activeobjects.tx.Transactional;
import com.learning.model.ProjectDTO;
import com.learning.model.ProjectEntity;

@Transactional
public interface ProjectRepository {
    ProjectEntity add(ProjectDTO project);
    ProjectEntity[] all();

    ProjectEntity getByProjectName(String projectName);
}
