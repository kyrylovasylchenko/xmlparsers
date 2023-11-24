package com.learning.model.DTO;

import com.atlassian.confluence.pages.Page;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageDTO {
    private Page page;
    private String bassKey;
    private String targetKey;
    private boolean isUpdated;
    private String body;
}
