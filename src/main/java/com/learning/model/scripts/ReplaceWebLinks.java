package com.learning.model.scripts;

import com.atlassian.confluence.setup.settings.Settings;
import com.learning.model.DTO.PageDTO;
import com.learning.model.Script;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReplaceWebLinks implements Script {

    @Override
    public PageDTO run(PageDTO page) {
        if(page.getBody().contains("href")){
            String newPattern1 = "<ac:link><ri:page ri:space-key=\"%s\"  ri:content-title=\"%s\" /><ac:plain-text-link-body><![CDATA[%s]]></ac:plain-text-link-body>//</ac:link>";
            String newPattern = "<ac:link><ri:page ri:content-title=\"%s\" /><ac:plain-text-link-body><![CDATA[%s]]></ac:plain-text-link-body>//</ac:link>";
            String body = page.getBody();
            Elements aLinks = hasALinks(body);


            for (Element link : aLinks) {
                String webLink = link.attr("href");
                String oldLink = link.toString();
                if (webLink.contains("https://bass.")) {

                    String[] strArr = webLink.split("/");
                    String spaceNewLink = strArr[strArr.length - 2];
                    String linkTitle = strArr[strArr.length - 1];
                    if (linkTitle.contains("?")) {
                        String[] linkTitleArr = linkTitle.split("\\?");
                        linkTitle = linkTitleArr[0];

                    }

                    linkTitle = linkTitle.replace("%5B", "[").replace("%5D", "]").replace("+", " ");
                    String newLink = "";
                    if (page.getTargetKey().equalsIgnoreCase(spaceNewLink)) {
                        if (!link.text().isEmpty()) {
                            newLink = String.format(newPattern, linkTitle, link.text());
                        } else {
                            newLink = String.format(newPattern, linkTitle, linkTitle);
                        }
                    } else {
                        if (!link.text().isEmpty()) {
                            newLink = String.format(newPattern1, spaceNewLink, linkTitle, link.text());

                        } else {
                            newLink = String.format(newPattern1, spaceNewLink, linkTitle, linkTitle);
                        }
                    }
                    body = body.replace(oldLink, newLink);
                }
            }
            Document pageDocument = Jsoup.parseBodyFragment(body);
            pageDocument.outputSettings().prettyPrint(false);

            page.setBody(pageDocument.html());
            page.setUpdated(true);
            page.getScriptLogs().put("ReplaceWebLinks", page.getPage().getTitle() + " || " + new Settings().getBaseUrl() + page.getPage().getUrlPath());
        }
        return page;
    }


    private Elements hasALinks(String body) {
        Document pageDocument = Jsoup.parseBodyFragment(body);
        pageDocument.outputSettings().prettyPrint(false);
        Element pageBodys = pageDocument.body();
        return pageBodys.select("a");
    }
}
