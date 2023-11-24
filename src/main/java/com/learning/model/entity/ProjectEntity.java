package com.learning.model.entity;



import net.java.ao.Entity;
import net.java.ao.Preload;
import net.java.ao.schema.StringLength;
import net.java.ao.schema.Unique;

@Preload
public interface ProjectEntity extends Entity {

    @Unique
    String getProjectName();

    void setProjectName(String projectName);

    String getBassProjectKey();

    void setBassProjectKey(String bassProjectKey);

    String getTargetProjectKey();

    void setTargetProjectKey(String targetProjectKey);

    @StringLength(StringLength.UNLIMITED)
    String getScripts();

    void setScripts(String scripts);
}
