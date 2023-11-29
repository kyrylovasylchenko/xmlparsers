package com.learning.model;

import com.learning.model.DTO.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class Task {
    private ProjectDTO projectDTO;
    private List<Script> scripts;
    private Map<String, List<String>> results;
}
