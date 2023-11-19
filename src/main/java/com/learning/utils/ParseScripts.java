package com.learning.utils;

import com.learning.model.ProjectDTO;
import com.learning.model.Script;
import com.learning.model.scripts.ReplaceSpaceKey;
import com.learning.model.scripts.ReplaceUserKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParseScripts {
    private final ReplaceUserKey replaceUserKey;
    private final ReplaceSpaceKey replaceSpaceKey;
    private final Map<String, Script> scripts;

    public ParseScripts(ReplaceUserKey replaceUserKey, ReplaceSpaceKey replaceSpaceKey) {
        this.replaceUserKey = replaceUserKey;
        this.replaceSpaceKey = replaceSpaceKey;
        this.scripts = Map.of("replaceUserKey", replaceSpaceKey,
                "ReplaceSpaceKey", replaceSpaceKey);
    }

    public List<Script> getScriptsForProject(ProjectDTO projectDTO){
        List<Script> scriptsResult = new ArrayList<>();
        for(String scriptName : projectDTO.getScripts()){
            scriptsResult.add(scripts.get(scriptName));
        }
        return scriptsResult;
    }
}
