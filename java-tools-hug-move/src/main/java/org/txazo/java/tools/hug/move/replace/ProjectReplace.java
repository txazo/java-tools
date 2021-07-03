package org.txazo.java.tools.hug.move.replace;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.txazo.java.tools.hug.move.bean.MavenDependency;
import org.txazo.java.tools.hug.move.bean.Module;
import org.txazo.java.tools.hug.move.bean.Project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
public class ProjectReplace {

    public static void replace(Project project) throws Exception {
        // 删除target、*.iml
        removeInvalidFile(project);
        // 扫描文件替换
        FileReplace.replace(project);
        // 替换pom
        replacePom(project);
        // 文件名、包路径替换
        if (project.getModuleList() != null) {
            for (Module m : project.getModuleList()) {
                PackageReplace.replace(m);
            }
        }
        PackageReplace.replace(project);
    }

    private static void replacePom(Project project) throws Exception {
        List<MavenDependency> mavenList = new ArrayList<>();
        mavenList.add(project.getMaven());
        if (CollectionUtils.isNotEmpty(project.getModuleList())) {
            project.getModuleList().forEach(m -> {
                if (m.getMaven().getGroupId() == null) {
                    m.getMaven().setGroupId(project.getMaven().getGroupId());
                }
                mavenList.add(m.getMaven());
            });
        }

        PomReplace.replace(project.getProjectFile(), mavenList);
        if (CollectionUtils.isNotEmpty(project.getModuleList())) {
            for (Module module : project.getModuleList()) {
                PomReplace.replace(module.getModuleFile(), mavenList);
            }
        }
    }

    private static void removeInvalidFile(Project project) throws Exception {
        removeInvalidFile(project.getProjectFile());
        if (CollectionUtils.isNotEmpty(project.getModuleList())) {
            for (Module module : project.getModuleList()) {
                removeInvalidFile(module.getModuleFile());
            }
        }
    }

    private static void removeInvalidFile(File file) throws Exception {
        deleteDirectory(new File(file.getAbsoluteFile() + "/target"));
        File[] childFileList = file.listFiles();
        if (childFileList != null && childFileList.length > 0) {
            for (File f : childFileList) {
                if (f.isFile() && f.getName().endsWith(".iml")) {
                    FileUtils.forceDelete(f);
                }
            }
        }
    }

    private static void deleteDirectory(File file) throws Exception {
        if (!file.exists() || !file.isDirectory()) {
            return;
        }

        File[] childFileList = file.listFiles();
        if (childFileList != null && childFileList.length > 0) {
            for (File f : childFileList) {
                if (f.isDirectory()) {
                    deleteDirectory(f);
                } else {
                    FileUtils.forceDelete(f);
                }
            }
        }
        FileUtils.deleteDirectory(file);
    }

}
