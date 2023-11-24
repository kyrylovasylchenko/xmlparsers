package com.learning.model.scripts;

import com.learning.model.DTO.PageDTO;
import com.learning.model.Script;

public class ReplaceCodeParam implements Script {
    @Override
    public PageDTO run(PageDTO page) {
        if(page.getBody().contains("ac:name=\"code\"")){
            page.setBody(page.getBody().replace("<ac:parameter ac:name=\"language\">json</ac:parameter>", ""));
            page.setUpdated(true);
        }
        return page;
    }
}
