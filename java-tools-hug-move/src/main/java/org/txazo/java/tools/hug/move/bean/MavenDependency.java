package org.txazo.java.tools.hug.move.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
@Data
public class MavenDependency implements Serializable {

    private String groupId;

    private String artifactId;

    private String version;

}
