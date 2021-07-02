package org.txazo.java.tools.hug.move;

import org.apache.commons.collections4.CollectionUtils;
import org.txazo.java.tools.hug.move.bean.Module;
import org.txazo.java.tools.hug.move.bean.Project;
import org.txazo.java.tools.hug.move.parse.ModuleParseUtil;
import org.txazo.java.tools.hug.move.parse.ProjectParseUtil;
import org.txazo.java.tools.hug.move.replace.ProjectReplace;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
public class ApplicationMain {

    public static void main(String[] args) throws Exception {
        String projectPath = "/Users/dzsb-000852/Bixin/order-base-service";
        Project project = ProjectParseUtil.parseProject(projectPath);
        if (CollectionUtils.isNotEmpty(project.getModuleNameList())) {
            List<Module> moduleList = new ArrayList<>();
            for (String moduleName : project.getModuleNameList()) {
                moduleList.add(ModuleParseUtil.parseModule(projectPath + "/" + moduleName));
            }
            project.setModuleList(moduleList);
        }

        ProjectReplace.replace(project);
    }

}
