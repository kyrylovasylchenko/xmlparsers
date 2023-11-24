package com.learning.model.scripts;

import com.atlassian.confluence.user.UserAccessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.atlassian.sal.api.user.UserKey;
import com.learning.model.PageDTO;
import com.learning.model.Script;

public class ReplaceUserKey implements Script {

    private final UserAccessor userAccessor;
    public ReplaceUserKey(UserAccessor userAccessor) {
        this.userAccessor = userAccessor;
    }

    @Override
    public PageDTO run(PageDTO page) {
        String body = page.getPage().getBodyAsString();
        if(body.contains("<ri:user ri:userkey=")){
            Pattern oldPattern = Pattern.compile("((<ac:link><ri:user ri:userkey=\")(.+?)(\" /></ac:link>))");
            Matcher matcher = oldPattern.matcher(body);
            while(matcher.find()){
                String oldLink = matcher.group(0);
                String userKey = matcher.group(3);

                String username = userAccessor.getExistingUserByKey(new UserKey(userKey)).getFullName();
                body = body.replace(oldLink, username);
            }
            page.setUpdated(true);
            page.setBody(body);
        }

        return page;
    }

}
