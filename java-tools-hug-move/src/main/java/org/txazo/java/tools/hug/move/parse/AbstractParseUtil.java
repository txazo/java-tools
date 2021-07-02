package org.txazo.java.tools.hug.move.parse;

import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.txazo.java.tools.hug.move.bean.MavenDependency;
import org.txazo.java.tools.hug.move.util.JsoupUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
public class AbstractParseUtil {

    protected static MavenDependency readProjectMaven(Document document) {
        MavenDependency mavenDependency = new MavenDependency();
        mavenDependency.setGroupId(JsoupUtil.readElementValue(document, "project > groupId"));
        mavenDependency.setArtifactId(JsoupUtil.readElementValue(document, "project > artifactId"));
        mavenDependency.setVersion(JsoupUtil.readElementValue(document, "project > version"));
        if (mavenDependency.getArtifactId() == null) {
            return null;
        }
        return mavenDependency;
    }

    protected static MavenDependency readParentMaven(Document document) {
        MavenDependency mavenDependency = new MavenDependency();
        mavenDependency.setGroupId(JsoupUtil.readElementValue(document, "project > parent > groupId"));
        mavenDependency.setArtifactId(JsoupUtil.readElementValue(document, "project > parent>  artifactId"));
        mavenDependency.setVersion(JsoupUtil.readElementValue(document, "project > parent > version"));
        if (mavenDependency.getArtifactId() == null) {
            return null;
        }
        return mavenDependency;
    }

    protected static MavenDependency readMavenDependency(Element element) {
        MavenDependency mavenDependency = new MavenDependency();
        mavenDependency.setGroupId(JsoupUtil.readElementValue(element, "groupId"));
        mavenDependency.setArtifactId(JsoupUtil.readElementValue(element, "artifactId"));
        mavenDependency.setVersion(JsoupUtil.readElementValue(element, "version"));
        if (mavenDependency.getArtifactId() == null) {
            return null;
        }
        return mavenDependency;
    }

    protected static List<MavenDependency> readMavenDependencyList(Element element, String cssQuery) {
        Elements elements = element.select(cssQuery);
        if (CollectionUtils.isEmpty(elements)) {
            return Collections.emptyList();
        }
        List<MavenDependency> mavenDependencyList = new ArrayList<>();
        for (Element e : elements) {
            mavenDependencyList.add(readMavenDependency(e));
        }
        return mavenDependencyList;
    }

}
