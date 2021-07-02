package org.txazo.java.tools.hug.move.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.txazo.java.tools.hug.move.bean.Project;
import org.txazo.java.tools.hug.move.util.JsoupUtil;

import java.io.File;
import java.io.IOException;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
public class ProjectParseUtil extends AbstractParseUtil {

    public static Project parseProject(String projectPath) throws IOException {
        File pomFile = new File(projectPath + "/pom.xml");
        if (!pomFile.exists()) {
            throw new RuntimeException(pomFile.getAbsolutePath() + "不存在");
        }

        Document document = Jsoup.parse(pomFile, "UTF-8");
        File projectFile = new File(projectPath);
        Project project = new Project();
        project.setProjectFile(projectFile);
        project.setProjectName(projectFile.getName());
        project.setMaven(readProjectMaven(document));
        project.setPackaging(JsoupUtil.readElementValue(document, "project > packaging"));
        project.setModuleNameList(JsoupUtil.readElementValueList(document, "project > modules > module"));
        project.setDependencyList(readMavenDependencyList(document, "project > dependencyManagement > dependencies > dependency"));
        project.getDependencyList().addAll(readMavenDependencyList(document, "project > dependencies > dependency"));
        project.setMainClass(JsoupUtil.readElementValue(document, "project mainClass"));
        return project;
    }

}
