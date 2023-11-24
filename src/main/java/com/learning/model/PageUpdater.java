package com.learning.model;

import com.atlassian.confluence.core.Modification;
import com.atlassian.confluence.pages.Page;

public class PageUpdater implements Modification<Page> {


    public final String body;

    public PageUpdater(String body) {
        this.body = body;
    }


    @Override
    public void modify(Page page) {
        page.setBodyAsString(body);
    }
}
