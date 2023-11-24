package com.learning.utils;

import com.atlassian.confluence.user.UserAccessor;
import com.learning.model.DTO.ProjectDTO;
import com.learning.model.Script;
import com.learning.model.scripts.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseScripts {

    private final Map<String, Script> scripts;

    public ParseScripts(UserAccessor userAccessor) {
        this.scripts = new HashMap<>();
        this.scripts.put("AddInfoMacro", new AddInfoMacro());
        this.scripts.put("ReplaceUserKey", new ReplaceUserKey(userAccessor));
        this.scripts.put("ReplaceSpaceKey", new ReplaceSpaceKey());
        this.scripts.put("ReplaceWebLinks", new ReplaceWebLinks());
        this.scripts.put("ReplacePageInfo", new ReplacePageInfo());
        this.scripts.put("ReplaceJiraMacro", new ReplaceJiraMacro());
        this.scripts.put("ReplaceCodeParam", new ReplaceCodeParam());
        this.scripts.put("ReplaceContentStatus", new ReplaceContentStatus());
        this.scripts.put("ReplaceNcTableFilter", new ReplaceNcTableFilter());
    }

    public List<Script> getScriptsForProject(ProjectDTO projectDTO){
        List<Script> scriptsResult = new ArrayList<>();
        for(String scriptName : projectDTO.getScripts()){
            scriptsResult.add(scripts.get(scriptName));
        }
        return scriptsResult;
    }
}
