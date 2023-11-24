package com.learning.model;

import com.learning.model.DTO.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Task {
    private ProjectDTO projectDTO;
    private List<Script> scripts;
}
