package com.learning.model.scripts;

import com.atlassian.confluence.setup.settings.Settings;
import com.learning.model.DTO.PageDTO;
import com.learning.model.Script;

public class ReplaceCodeParam implements Script {
    @Override
    public PageDTO run(PageDTO page) {
        if(page.getBody().contains("ac:name=\"code\"")){
            page.setBody(page.getBody().replace("<ac:parameter ac:name=\"language\">json</ac:parameter>", ""));
            page.setUpdated(true);
            page.getScriptLogs().put("ReplaceCodeParam", page.getPage().getTitle() + " || " + new Settings().getBaseUrl() + page.getPage().getUrlPath());
        }
        return page;
    }
}
