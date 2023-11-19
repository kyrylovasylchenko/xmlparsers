package com.learning.config;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.confluence.pages.PageManager;
import com.atlassian.confluence.security.PermissionManager;
import com.atlassian.confluence.user.UserAccessor;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.sal.api.user.UserManager;
import org.springframework.context.annotation.Bean;


import static com.atlassian.plugins.osgi.javaconfig.OsgiServices.importOsgiService;

public class ConfluenceBeans {

    @Bean
    public ApplicationProperties applicationProperties() {
        return importOsgiService(ApplicationProperties.class);
    }

    @Bean
    public PageManager pageManager(){
        return importOsgiService(PageManager.class);
    }

    @Bean
    public UserAccessor userAccessor(){
        return importOsgiService(UserAccessor.class);
    }

    @Bean
    public UserManager userManager(){
        return importOsgiService(UserManager.class);
    }

    @Bean
    public PermissionManager permissionManager(){
        return importOsgiService(PermissionManager.class);
    }

    @Bean
    public ActiveObjects activeObjects(){
        return importOsgiService(ActiveObjects.class);
    }

}
