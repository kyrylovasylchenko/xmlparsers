package com.learning.model.scripts;

import com.atlassian.confluence.setup.settings.Settings;
import com.learning.model.DTO.PageDTO;
import com.learning.model.Script;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceSpaceKey implements Script {
    @Override
    public PageDTO run(PageDTO page) {
        if(page.getBody().contains("ri:space-key=")){
            Set<String> keys = new HashSet<>();
            Pattern pattern = Pattern.compile("ri:space-key=\"(.*?)\"");
            String body = page.getBody();
            Matcher matcher = pattern.matcher(body);

            while (matcher.find()) {
                String key = matcher.group(1);
                if(!key.equalsIgnoreCase("@self")&& !key.equalsIgnoreCase("pages") && !key.equals(page.getTargetKey())){
                    keys.add(matcher.group(1));
                }
            }

            if(!keys.isEmpty()){
                for (String keyOld : keys) {
                    body = body.replace("ri:space-key=\"" + keyOld, "ri:space-key=\"" + page.getTargetKey());
                }
                page.setBody(body);
                page.setUpdated(true);
                page.getScriptLogs().put("ReplaceSpaceKey", page.getPage().getTitle() + " || " + new Settings().getBaseUrl() + page.getPage().getUrlPath());
            }
        }

        return page;
    }
}
