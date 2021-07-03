package org.txazo.java.tools.hug.move.bean;

import lombok.Data;

import java.io.File;
import java.util.List;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
@Data
public class Project {

    private File projectFile;

    private String projectName;

    private String newProjectName;

    private MavenDependency maven;

    private String packaging;

    private List<String> moduleNameList;

    private List<Module> moduleList;

    private List<MavenDependency> dependencyList;

    private String buildFinalName;

    private String mainClass;

    private String basePackage;

    private String newBasePackage;

    private String testBasePackage;

    private String newTestBasePackage;

}
