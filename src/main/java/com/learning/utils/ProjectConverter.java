package com.learning.utils;

import com.learning.model.ProjectDTO;
import com.learning.model.ProjectEntity;

import java.util.ArrayList;
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
        List<String> result = new ArrayList<>();
        for (String script : scripts) {
            result.add(script.trim());
        }
        return result;
    }
}
