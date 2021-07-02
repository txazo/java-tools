package org.txazo.java.tools.hug.move.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.txazo.java.tools.hug.move.bean.Module;
import org.txazo.java.tools.hug.move.bean.Project;
import org.txazo.java.tools.hug.move.util.JsoupUtil;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
public class ModuleParseUtil extends AbstractParseUtil {

    public static Module parseModule(Project project, String modulePath) throws IOException {
        File pomFile = new File(modulePath + "/pom.xml");
        if (!pomFile.exists()) {
            throw new RuntimeException(pomFile.getAbsolutePath() + "不存在");
        }

        Document document = Jsoup.parse(pomFile, "UTF-8");
        File moduleFile = new File(modulePath);
        Module module = new Module();
        module.setModuleFile(moduleFile);
        module.setModuleFileName(moduleFile.getName());
        module.setParent(readParentMaven(document));
        module.setMaven(readProjectMaven(document));
        module.setName(JsoupUtil.readElementValue(document, "project > name"));
        module.setDependencyList(readMavenDependencyList(document, "project > dependencies > dependency"));
        module.setBuildFinalName(JsoupUtil.readElementValue(document, "project > build > finalName"));
        module.setMainClass(JsoupUtil.readElementValue(document, "project mainClass"));
        parseJavaBasePackage(module);
        checkModuleName(module);
        if (module.getBuildFinalName() != null &&
                !Objects.equals(module.getBuildFinalName(), module.getMaven().getArtifactId()) &&
                !Objects.equals(module.getBuildFinalName(), project.getProjectName())) {
            throw new RuntimeException(project + " buildFinalName不一致");
        }
        return module;
    }

    private static void checkModuleName(Module module) {
        if (!Objects.equals(module.getMaven().getArtifactId(), module.getModuleFileName())) {
            throw new RuntimeException(module.getModuleFileName() + " artifactId和模块名不一致");
        }
        if (module.getName() != null &&
                !Objects.equals(module.getMaven().getArtifactId(), module.getName())) {
            throw new RuntimeException(module.getModuleFileName() + " artifactId和name不一致");
        }
    }

}
