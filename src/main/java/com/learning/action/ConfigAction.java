package com.learning.action;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.atlassian.confluence.security.PermissionManager;
import com.atlassian.confluence.user.ConfluenceUser;


public class ConfigAction extends ConfluenceActionSupport {

    private final PermissionManager permissionManager;

    public ConfigAction(PermissionManager permissionManager) {
        this.permissionManager = permissionManager;
    }

    public String execute(){
        ConfluenceUser user = getAuthenticatedUser();
        if(permissionManager.isConfluenceAdministrator(user)){
            return "run";
        }else {
            return "error/403";
        }
    }
}
