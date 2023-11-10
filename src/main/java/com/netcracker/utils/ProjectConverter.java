package com.netcracker.utils;

import com.netcracker.model.ProjectDTO;
import com.netcracker.model.ProjectEntity;

import java.util.List;

public class ProjectConverter {

    public ProjectDTO projectEntityToProjectDTO(ProjectEntity project){
        return ProjectDTO.builder().projectName(project.getProjectName())
                .bassProjectKey(project.getBassProjectKey())
                .targetProjectKey(project.getTargetProjectKey())
                .scripts(parseScripts(project.getScripts()))
                .build();
    }


    public List<String> parseScripts(String scriptsSTR){
        String[] scripts = scriptsSTR.split(",");
        scripts[0] = scripts[0].replace("[","");
        scripts[scripts.length - 1] = scripts[scripts.length - 1].replace("]","");
        return List.of(scripts);
    }
}
