package org.txazo.java.tools.hug.move.util;

import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
public class JsoupUtil {

    public static String readElementValue(Element element, String cssQuery) {
        Elements elements = element.select(cssQuery);
        if (CollectionUtils.isEmpty(elements)) {
            return null;
        }

        return elements.get(0).childNodes().get(0).outerHtml().trim().replaceAll("\n", "");
    }

    public static List<String> readElementValueList(Element element, String cssQuery) {
        Elements elements = element.select(cssQuery);
        if (CollectionUtils.isEmpty(elements)) {
            return null;
        }

        List<String> list = new ArrayList<>();
        for (Element e : elements) {
            list.add(e.childNodes().get(0).outerHtml().trim().replaceAll("\n", ""));
        }
        return list;
    }

}
