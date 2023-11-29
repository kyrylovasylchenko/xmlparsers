package com.learning.model.scripts;

import com.atlassian.confluence.setup.settings.Settings;
import com.learning.model.DTO.PageDTO;
import com.learning.model.Script;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplacePageInfo implements Script {
    @Override
    public PageDTO run(PageDTO page) {
        if(page.getBody().contains("ac:name=\"page-info\"")){
            Pattern oldPattern = Pattern.compile("(<ac:structured-macro ac:name=\"page-info\"(.*?)(<ac:parameter ac:name=\"\">tinyurl</ac:parameter></ac:structured-macro>))");
            Pattern oldPattern2 = Pattern.compile("<ac:macro ac:name=\"page-info\"><ac:default-parameter>tinyurl</ac:default-parameter></ac:macro></p></div></td></tr>");
            String body = page.getBody();
            String title = page.getPage().getTitle();
            Matcher matcher = oldPattern.matcher(body);
            Matcher matcher2 = oldPattern2.matcher(body);
            if (body.contains("ac:default-parameter")) {
                while (matcher2.find()) {
                    String oldLink = matcher2.group();
                    body = body.replace(oldLink, title);

                }
            } else {
                while (matcher.find()) {
                    String oldLink = matcher.group();
                    body = body.replace(oldLink, title);
                }
            }
            page.setBody(body);
            page.setUpdated(true);
            page.getScriptLogs().put("ReplacePageInfo", page.getPage().getTitle() + " || " + new Settings().getBaseUrl() + page.getPage().getUrlPath());
        }
        return page;
    }
}
