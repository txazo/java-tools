package org.txazo.java.tools.hug.move.bean;

import lombok.Data;

import java.io.File;
import java.util.List;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
@Data
public class Module {

    private File moduleFile;

    private String moduleFileName;

    private MavenDependency parent;

    private MavenDependency maven;

    private String name;

    private List<MavenDependency> dependencyList;

    private String buildFinalName;

    private String mainClass;

    private String basePackage;

    private String newBasePackage;

    private String testBasePackage;

    private String newTestBasePackage;

}
