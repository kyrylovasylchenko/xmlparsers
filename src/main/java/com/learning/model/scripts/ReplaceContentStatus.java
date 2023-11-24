package com.learning.model.scripts;

import com.learning.model.DTO.PageDTO;
import com.learning.model.Script;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReplaceContentStatus implements Script {
    @Override
    public PageDTO run(PageDTO page) {
        if(page.getBody().contains("ac:name=\"content-status\"")){
            Document pageDocument = Jsoup.parseBodyFragment(page.getBody());
            pageDocument.outputSettings().prettyPrint(false);
            Element pageBodys = pageDocument.body();
            Elements ppElements = pageBodys.select("[ac:name=\"content-status\"]");
            String newLinks = "";
            for (Element ppElement : ppElements) {
                String status = ppElement.select("ac|parameter[ac:name=\"Status\"]").first().text();
                newLinks = "<ac:structured-macro ac:name=\"status\" ac:schema-version=\"1\"><ac:parameter ac:name=\"colour\">Green</ac:parameter><ac:parameter ac:name=\"title\">" + status + "</ac:parameter></ac:structured-macro>";
                ppElement.empty();
                ppElement.parent().html(newLinks);
            }
            page.setBody(pageDocument.html());
            page.setUpdated(true);
        }
        return page;
    }
}
