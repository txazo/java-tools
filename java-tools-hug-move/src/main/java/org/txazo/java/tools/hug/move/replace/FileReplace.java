package org.txazo.java.tools.hug.move.replace;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.txazo.java.tools.hug.move.bean.Module;
import org.txazo.java.tools.hug.move.bean.Project;

import java.io.File;
import java.util.List;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
public class FileReplace {

    private static final String HUG_PREFIX = "hug-";

    public static void replace(Project project) throws Exception {
        replaceDirectory(project, project.getProjectFile());
    }

    private static void replaceDirectory(Project project, File file) throws Exception {
        if (file.getName().equals(".git")) {
            return;
        }
        File[] childFileList = file.listFiles();
        if (childFileList != null && childFileList.length > 0) {
            for (File f : childFileList) {
                if (f.isDirectory()) {
                    replaceDirectory(project, f);
                } else {
                    replaceFile(project, f);
                }
            }
        }
    }

    private static void replaceFile(Project project, File file) throws Exception {
        List<String> lines = FileUtils.readLines(file, "UTF-8");
        if (CollectionUtils.isNotEmpty(lines)) {
            for (int i = 0; i < lines.size(); i++) {
                lines.set(i, replaceLine(project, file, lines.get(i)));
            }
        }
        FileUtils.writeLines(file, "UTF-8", lines);
    }

    private static String replaceLine(Project project, File file, String line) {
        if (file.getName().endsWith(".java")) {
            return replaceJavaPackage(project, file, line);
        } else if (file.getAbsolutePath().endsWith("/resources/META-INF/app.properties")) {
            return replaceCatAppProperties(project, file, line);
        } else if (file.getAbsolutePath().endsWith("/resources/application.properties")) {
            return replaceApplicationProperties(project, file, line);
        } else if (file.getAbsolutePath().contains("/resources/")) {
            return replaceJavaPackage(project, file, line);
        }
        return line;
    }

    private static String replaceJavaPackage(Project project, File file, String line) {
        if (project.getBasePackage() != null) {
            line = line.replaceAll(project.getBasePackage(), project.getNewBasePackage());
        }
        if (project.getModuleList() != null) {
            for (Module module : project.getModuleList()) {
                if (module.getBasePackage() != null) {
                    line = line.replaceAll(module.getBasePackage(), module.getNewBasePackage());
                }
                if (file.getAbsolutePath().startsWith(module.getModuleFile().getAbsolutePath()) &&
                        file.getAbsolutePath().contains("src/test/java")) {
                    line = line.replaceAll(module.getTestBasePackage(), module.getNewTestBasePackage());
                }
            }
        }
        return line;
    }

    private static String replaceCatAppProperties(Project project, File file, String line) {
        if (!line.contains(HUG_PREFIX) && line.trim().startsWith("app.id") || line.trim().startsWith("app.name")) {
            return line.replaceAll(project.getProjectName(), project.getNewProjectName());
        }
        return line;
    }

    private static String replaceApplicationProperties(Project project, File file, String line) {
        if (!line.contains(HUG_PREFIX) && line.trim().startsWith("spring.application.name")) {
            return line.replaceAll(project.getProjectName(), project.getNewProjectName());
        }
        return line;
    }

}
