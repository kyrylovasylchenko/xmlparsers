package com.learning.services;

import com.learning.model.DTO.ProjectDTO;
import com.learning.model.entity.ProjectEntity;
import com.learning.repository.ProjectRepository;
import com.learning.utils.ProjectConverter;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    private final ProjectRepository projectRepository;
    //SHOULD IT BE COMPONENT?
    private final ProjectConverter projectConverter = new ProjectConverter();

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDTO> getAllProjects(){
        ProjectEntity[] all = projectRepository.all();
        List<ProjectDTO> result = new ArrayList<>();
        for(ProjectEntity projectEntity : all){
            result.add(projectConverter.projectEntityToProjectDTO(projectEntity));
        }
        return result;
    }

    public ProjectDTO add(ProjectDTO projectDTO){
        ProjectEntity saved = projectRepository.add(projectDTO);
        return projectConverter.projectEntityToProjectDTO(saved);
    }

    public ProjectDTO getProjectByName(String projectName) {

        ProjectEntity byProjectName = projectRepository.getByProjectName(projectName);
        return projectConverter.projectEntityToProjectDTO(byProjectName);
    }

    public void deleteProjectByName(String projectName){
        projectRepository.deleteProjectByName(projectName);
    }
}
