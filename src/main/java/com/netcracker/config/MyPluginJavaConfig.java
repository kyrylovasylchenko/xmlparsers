package com.netcracker.config;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.confluence.pages.PageManager;
import com.atlassian.confluence.security.PermissionManager;
import com.atlassian.confluence.user.UserAccessor;

import com.atlassian.sal.api.ApplicationProperties;

import com.atlassian.sal.api.user.UserManager;
import com.netcracker.repository.ProjectRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static com.atlassian.plugins.osgi.javaconfig.OsgiServices.importOsgiService;

@Configuration
@Import({ConfluenceBeans.class,
CustomBeans.class})
public class MyPluginJavaConfig {

}