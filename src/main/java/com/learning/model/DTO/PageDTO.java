package com.learning.model.DTO;

import com.atlassian.confluence.pages.Page;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class PageDTO {
    private Page page;
    private String bassKey;
    private String targetKey;
    private boolean isUpdated;
    private String body;
    private Map<String, String> scriptLogs;
}
