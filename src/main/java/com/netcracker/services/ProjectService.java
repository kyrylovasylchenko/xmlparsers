package com.netcracker.services;

import com.netcracker.model.ProjectDTO;
import com.netcracker.model.ProjectEntity;
import com.netcracker.repository.ProjectRepository;
import com.netcracker.utils.ProjectConverter;

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
}
