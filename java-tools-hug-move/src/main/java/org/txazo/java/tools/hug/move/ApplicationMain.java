package org.txazo.java.tools.hug.move;

import org.apache.commons.collections4.CollectionUtils;
import org.txazo.java.tools.hug.move.bean.Module;
import org.txazo.java.tools.hug.move.bean.Project;
import org.txazo.java.tools.hug.move.parse.ModuleParseUtil;
import org.txazo.java.tools.hug.move.parse.ProjectParseUtil;
import org.txazo.java.tools.hug.move.replace.ProjectReplace;
import org.txazo.java.tools.hug.move.util.ShellUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
public class ApplicationMain {

    public static void main(String[] args) throws Exception {
        String workPath = "/Users/dzsb-000852/test";
        String projectName = "bixin-biggie-service";
        String projectPath = workPath + "/" + projectName;
        String newProjectPath = workPath + "/hug-" + projectName;
        String originGit = "git@git.yupaopao.com:bixin/user/" + projectName + ".git";
        String destGit = "git@git.yupaopao.com:hugging/trade-international/platform/hug-" + projectName + ".git";

        ShellUtil.runShell("rm -rf " + projectName, new File(workPath), true);
        ShellUtil.runShell("rm -rf hug-" + projectName, new File(workPath), true);
        ShellUtil.runShell("git clone -b master " + originGit, new File(workPath), false);
        ShellUtil.runShell("cd " + projectName, new File(workPath), false);
        ShellUtil.runShell("rm -rf .git", new File(projectPath), false);

        Project project = ProjectParseUtil.parseProject(projectPath);
        if (CollectionUtils.isNotEmpty(project.getModuleNameList())) {
            List<Module> moduleList = new ArrayList<>();
            for (String moduleName : project.getModuleNameList()) {
                moduleList.add(ModuleParseUtil.parseModule(project, projectPath + "/" + moduleName));
            }
            project.setModuleList(moduleList);
        }

        System.out.println("开始执行项目替换...");
        ProjectReplace.replace(project);

//        ShellUtil.runShell("git init", new File(newProjectPath), true);
//        ShellUtil.runShell("git remote add origin " + destGit, new File(newProjectPath), false);
//        ShellUtil.runShell("git add .", new File(newProjectPath), false);
//        ShellUtil.runShell("git commit -am \"Initial Commit\"", new File(newProjectPath), false);
//        ShellUtil.runShell("git push origin -f master", new File(newProjectPath), false);

        System.out.println("执行成功");
    }

}
