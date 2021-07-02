package org.txazo.java.tools.hug.move.parse;

import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.txazo.java.tools.hug.move.bean.Module;
import org.txazo.java.tools.hug.move.util.JsoupUtil;

import java.io.File;
import java.io.IOException;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
public class ModuleParseUtil extends AbstractParseUtil {

    public static Module parseModule(String modulePath) throws IOException {
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
        parseModuleBasePackage(module);
        return module;
    }

    public static void parseModuleBasePackage(Module module) {
        module.setBasePackage(readModuleBasePackage(module.getModuleFile().getAbsolutePath() + "/src/main/java/com/yupaopao"));
        module.setTestBasePackage(readModuleBasePackage(module.getModuleFile().getAbsolutePath() + "/src/main/test/com/yupaopao"));
    }

    private static String readModuleBasePackage(String basePath) {
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

    private static boolean isLastBasePackage(File file) {
        File[] childFiles = file.listFiles();
        return ArrayUtils.isEmpty(childFiles) ||
                !(childFiles != null && childFiles.length == 1 && childFiles[0].isDirectory());
    }

}
