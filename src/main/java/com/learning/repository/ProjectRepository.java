package com.learning.repository;

import com.atlassian.activeobjects.tx.Transactional;
import com.learning.model.DTO.ProjectDTO;
import com.learning.model.entity.ProjectEntity;

@Transactional
public interface ProjectRepository {
    ProjectEntity add(ProjectDTO project);
    ProjectEntity[] all();

    ProjectEntity getByProjectName(String projectName);
}
