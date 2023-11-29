package com.learning.model.scripts;

import com.learning.model.DTO.PageDTO;
import com.learning.model.Script;

public class AddInfoMacro implements Script {


    @Override
    public PageDTO run(PageDTO page) {
        if(!page.getBody().contains("ac:name=\"info\"")){
            page.setBody("<ac:structured-macro ac:name=\"info\" ac:schema-version=\"1\"><ac:rich-text-body><p>" +
                    "<strong><span style=\"color: rgb(23,43,77);\"><span style=\"color: rgb(128,0,0);\">" +
                    "This content is provided for reading only and must not be edited. <br />All page reviews are performed via JIRA tickets. " +
                    "<br /></span></span></strong><strong><span style=\"color: rgb(23,43,77);\"><span style=\"color: rgb(128,0,0);\">" +
                    "In case you'd like to update the content, please create a JIRA ticket " +
                    "<a href=\"https://jsd.netcracker.com/projects/NISD\">https://jsd.netcracker.com/projects/NISD</a>.</span></span></strong>" +
                    "</p></ac:rich-text-body></ac:structured-macro>" + page.getBody());
            page.setUpdated(true);
            page.getScriptLogs().put("AddInfoMacro", page.getPage().getTitle());
        }
        return page;
    }
}
