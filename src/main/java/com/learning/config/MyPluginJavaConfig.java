package com.learning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static com.atlassian.plugins.osgi.javaconfig.OsgiServices.importOsgiService;

@Configuration
@Import({ConfluenceBeans.class,
CustomBeans.class})
public class MyPluginJavaConfig {

}