package com.learning.model.scripts;

import com.atlassian.confluence.setup.settings.Settings;
import com.learning.model.DTO.PageDTO;
import com.learning.model.Script;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReplaceJiraMacro implements Script {
    @Override
    public PageDTO run(PageDTO page) {
        if(page.getBody().contains("ac:name=\"jira\"")){
            Document pageDocument = Jsoup.parseBodyFragment(page.getBody());
            pageDocument.outputSettings().prettyPrint(false);
            Element pageBodys = pageDocument.body();
            Elements jiraElements = pageBodys.select("[ac:name=\"jira\"]");
            for (Element jiraElement : jiraElements) {
                if (jiraElement.getElementsByAttributeValue("ac:name", "server").text().equals("NetCracker TMS")) {
                    String key = jiraElement.getElementsByAttributeValue("ac:name", "key").text();
                    Element elKey = new Element("span");
                    elKey.appendText(key);
                    jiraElement.replaceWith(elKey);

                }
            }
            page.setBody(pageDocument.html());
            page.setUpdated(true);
            page.getScriptLogs().put("ReplaceJiraMacro", page.getPage().getTitle() + " || " + new Settings().getBaseUrl() + page.getPage().getUrlPath());
        }
        return page;
    }
}
