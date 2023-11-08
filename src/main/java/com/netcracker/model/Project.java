package com.netcracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @JsonProperty("projectName")
    private String name;

    @JsonProperty("bassProjectKey")
    private String bassKey;

    @JsonProperty("targetProjectKey")
    private String targetKey;

    @JsonProperty("scripts")
    private List<String> scripts;


}
