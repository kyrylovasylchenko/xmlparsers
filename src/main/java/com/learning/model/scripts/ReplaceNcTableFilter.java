package com.learning.model.scripts;

import com.atlassian.confluence.setup.settings.Settings;
import com.learning.model.DTO.PageDTO;
import com.learning.model.Script;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReplaceNcTableFilter implements Script {
    @Override
    public PageDTO run(PageDTO page) {
        if(page.getBody().contains("ac:name=\"nc-table-filter\"")){
            Document pageDocument = Jsoup.parseBodyFragment(page.getBody());
            pageDocument.outputSettings().prettyPrint(false);
            Element pageBodys = pageDocument.body();
            Elements ppElements = pageBodys.select("[ac:name=\"nc-table-filter\"]");
            for (Element ppElement : ppElements) {

                ppElement.attr("ac:name", "table-filter");

            }
            page.setBody(pageDocument.html());
            page.setUpdated(true);
            page.getScriptLogs().put("ReplaceNcTableFilter", page.getPage().getTitle() + " || " + new Settings().getBaseUrl() + page.getPage().getUrlPath());
        }
        return page;
    }
}
