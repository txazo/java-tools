package org.txazo.java.tools.hug.move.replace;

import org.txazo.java.tools.hug.move.bean.Module;
import org.txazo.java.tools.hug.move.bean.Project;
import org.txazo.java.tools.hug.move.util.ShellUtil;

import java.io.File;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
public class PackageReplace {

    public static void replace(Project project) throws Exception {
        replacePackage(project.getProjectFile(), project.getBasePackage(), false);
        replacePackage(project.getProjectFile(), project.getTestBasePackage(), true);
        // 模块重命名
        ShellUtil.runShell(String.format("cd ..; mv %s %s",
                project.getProjectName(), "hug-" + project.getProjectName()),
                project.getProjectFile(), true);
    }

    public static void replace(Module module) throws Exception {
        replacePackage(module.getModuleFile(), module.getBasePackage(), false);
        replacePackage(module.getModuleFile(), module.getTestBasePackage(), true);
        // 模块重命名
        ShellUtil.runShell(String.format("cd ..; mv %s %s",
                module.getModuleFileName(), "hug-" + module.getModuleFileName()),
                module.getModuleFile(), true);
    }

    public static void replacePackage(File file, String basePackage, boolean test) throws Exception {
        if (basePackage == null) {
            return;
        }
        File yupaopaoFile = new File(file.getAbsolutePath() +
                (test ? "/src/test/java/com/yupaopao" : "/src/main/java/com/yupaopao"));
        if (!yupaopaoFile.exists()) {
            return;
        }
        String leftPackage = basePackage.replaceAll("com.yupaopao.", "");
        String subName = leftPackage.substring(0, leftPackage.indexOf("."));

        // 修改包路径
        ShellUtil.runShell("mkdir hug", yupaopaoFile, true);
        ShellUtil.runShell(String.format("mv %s hug", subName), yupaopaoFile, false);
    }

}
