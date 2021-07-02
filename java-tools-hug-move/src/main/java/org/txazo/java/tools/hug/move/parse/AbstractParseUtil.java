package org.txazo.java.tools.hug.move.parse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.txazo.java.tools.hug.move.bean.MavenDependency;
import org.txazo.java.tools.hug.move.bean.Module;
import org.txazo.java.tools.hug.move.bean.Project;
import org.txazo.java.tools.hug.move.util.JsoupUtil;

import java.io.File;
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

    protected static void parseJavaBasePackage(Project project) {
        project.setBasePackage(readJavaBasePackage(project.getProjectFile().getAbsolutePath() + "/src/main/java/com/yupaopao"));
        project.setTestBasePackage(readJavaBasePackage(project.getProjectFile().getAbsolutePath() + "/src/test/java/com/yupaopao"));
        if (project.getBasePackage() != null) {
            project.setNewBasePackage(project.getBasePackage().replace("com.yupaopao", "com.yupaopao.hug"));
        }
        if (project.getTestBasePackage() != null) {
            project.setNewTestBasePackage(project.getTestBasePackage().replace("com.yupaopao", "com.yupaopao.hug"));
        }
    }

    protected static void parseJavaBasePackage(Module module) {
        module.setBasePackage(readJavaBasePackage(module.getModuleFile().getAbsolutePath() + "/src/main/java/com/yupaopao"));
        module.setTestBasePackage(readJavaBasePackage(module.getModuleFile().getAbsolutePath() + "/src/test/java/com/yupaopao"));
        if (module.getBasePackage() != null) {
            module.setNewBasePackage(module.getBasePackage().replace("com.yupaopao", "com.yupaopao.hug"));
        }
        if (module.getTestBasePackage() != null) {
            module.setNewTestBasePackage(module.getTestBasePackage().replace("com.yupaopao", "com.yupaopao.hug"));
        }
    }

    protected static String readJavaBasePackage(String basePath) {
        File currentFile = new File(basePath);
        if (!currentFile.exists()) {
            return null;
        }
        while (!isLastBasePackage(currentFile)) {
            File[] childFiles = currentFile.listFiles();
            if (childFiles == null || childFiles.length < 1) {
                break;
            }
            currentFile = childFiles[0];
        }
        String basePackage = currentFile.getAbsolutePath();
        return basePackage.substring(basePackage.indexOf("com/yupaopao")).replaceAll("/", ".");
    }

    protected static boolean isLastBasePackage(File file) {
        File[] childFiles = file.listFiles();
        return ArrayUtils.isEmpty(childFiles) ||
                !(childFiles != null && childFiles.length == 1 && childFiles[0].isDirectory());
    }

}
