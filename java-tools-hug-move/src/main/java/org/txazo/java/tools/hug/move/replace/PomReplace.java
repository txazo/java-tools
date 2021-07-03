package org.txazo.java.tools.hug.move.replace;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.txazo.java.tools.hug.move.bean.MavenDependency;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
public class PomReplace {

    private static final Pattern START_TAG = Pattern.compile("^\\s*<[^!/<>]+>\\s*$");
    private static final Pattern END_TAG = Pattern.compile("^\\s*</[^!/<>]+>$\\s*");
    private static final Pattern FULL_TAG = Pattern.compile("^\\s*<[^!/<>]+>[^<>]*</[^!/<>]+>\\s*$");

    private static String PATH = "project";

    private static String tempGroupIdLine;
    private static int tempGroupIdLineNum;
    private static String tempArtifactIdLine;
    private static int tempArtifactIdLineNum;

    private static List<String> tempDependencyList = new ArrayList<>();
    private static List<String> tempExclusionList = new ArrayList<>();

    public static void replace(File file, List<MavenDependency> mavenList) throws Exception {
        File pomFile = new File(file.getAbsolutePath() + "/pom.xml");
        List<String> lines = FileUtils.readLines(pomFile, "UTF-8");
        List<String> result = new ArrayList<>(lines.size() + 1);
        if (CollectionUtils.isNotEmpty(lines)) {
            boolean beginSkip = false;
            for (String line : lines) {
                if (!beginSkip) {
                    if (line.startsWith("<?xml")) {
                        result.add(line);
                        continue;
                    }
                    if (line.endsWith(">")) {
                        beginSkip = true;
                    }
                    result.add(line);
                } else {
                    if (START_TAG.matcher(line).matches()) {
                        PATH = PATH + "." + parseTag(line);
                        if (matchMavenExclusionsTag()) {
                            tempExclusionList.add(line);
                        } else {
                            result.add(line);
                        }
                    } else if (END_TAG.matcher(line).matches()) {
                        String tag = parseTag(line);
                        boolean addLine = true;
                        if (matchMavenExclusionsTag()) {
                            tempExclusionList.add(line);
                            addLine = false;
                        }
                        if (!PATH.equals("project")) {
                            PATH = PATH.substring(0, PATH.lastIndexOf("."));
                        }
                        if (matchMavenEndTag(tag)) {
                            replaceMaven(result, mavenList);
                        }
                        if (addLine) {
                            result.add(line);
                        }
                    } else if (FULL_TAG.matcher(line).matches()) {
                        String tag = parseTag(line);
                        if (matchMavenTag()) {
                            if (matchMavenTag("groupId", tag)) {
                                tempGroupIdLine = line;
                                tempGroupIdLineNum = tempDependencyList.size();
                            } else if (matchMavenTag("artifactId", tag)) {
                                tempArtifactIdLine = line;
                                tempArtifactIdLineNum = tempDependencyList.size();
                            }
                            tempDependencyList.add(line);
                        } else if (matchMavenExclusionsTag()) {
                            tempExclusionList.add(line);
                        } else if (matchTag("project.groupId", tag)) {
                            result.add(line.replace("com.yupaopao.", "com.yupaopao.hug."));
                        } else if (matchTag("project.artifactId", tag)) {
                            result.add(appendHugToTagText(line));
                        } else if (matchTag("project.name", tag)) {
                            result.add(appendHugToTagText(line));
                        } else if (matchTag("project.modules.module", tag)) {
                            result.add(appendHugToTagText(line));
                        } else if (matchTag("project.build.finalName", tag)) {
                            result.add(appendHugToTagText(line));
                        } else if (matchTag("project.build.plugins.plugin.configuration.mainClass", tag)) {
                            result.add(line.replace("com.yupaopao.", "com.yupaopao.hug."));
                        } else {
                            result.add(line);
                        }
                    } else {
                        result.add(line);
                    }
                }
            }
        }
        FileUtils.writeLines(pomFile, "UTF-8", result);
    }

    private static String parseTag(String line) {
        line = line.trim().replaceAll("/", "");
        return line.substring(1, line.indexOf(">"));
    }

    private static boolean matchMavenTag() {
        return PATH.equals("project.parent") ||
                PATH.equals("project.dependencies.dependency") ||
                PATH.equals("project.dependencyManagement.dependencies.dependency");
    }

    private static boolean matchMavenTag(String subTag, String tag) {
        return matchTag("project.parent." + subTag, tag) ||
                matchTag("project.dependencies.dependency." + subTag, tag) ||
                matchTag("project.dependencyManagement.dependencies.dependency." + subTag, tag);
    }

    private static boolean matchMavenExclusionsTag() {
        return PATH.startsWith("project.parent.exclusions") ||
                PATH.startsWith("project.dependencies.dependency.exclusions") ||
                PATH.startsWith("project.dependencyManagement.dependencies.dependency.exclusions");
    }

    private static boolean matchMavenEndTag(String tag) {
        return matchTag("project.parent", tag) ||
                matchTag("project.dependencies.dependency", tag) ||
                matchTag("project.dependencyManagement.dependencies.dependency", tag);
    }

    private static boolean matchTag(String path, String tag) {
        return (PATH + "." + tag).equals(path);
    }

    private static String appendHugToTagText(String line) {
        int index = line.indexOf(">");
        return line.substring(0, index + 1) + "hug-" + line.substring(index + 1);
    }

    private static void replaceMaven(List<String> result, List<MavenDependency> mavenList) {
        boolean match = false;
        String tempGroupId = tempGroupIdLine.trim()
                .replaceAll("<[^!/<>]+>", "")
                .replaceAll("</[^!/<>]+>", "");
        String tempArtifactId = tempArtifactIdLine.trim()
                .replaceAll("<[^!/<>]+>", "")
                .replaceAll("</[^!/<>]+>", "");
        for (MavenDependency maven : mavenList) {
            if (Objects.equals(maven.getGroupId(), tempGroupId) &&
                    Objects.equals(maven.getArtifactId(), tempArtifactId)) {
                match = true;
                break;
            }
        }

        for (int i = 0; i < tempDependencyList.size(); i++) {
            String line = tempDependencyList.get(i);
            if (match && i == tempGroupIdLineNum) {
                result.add(line.replace("com.yupaopao.", "com.yupaopao.hug."));
            } else if (match && i == tempArtifactIdLineNum) {
                result.add(appendHugToTagText(line));
            } else {
                result.add(line);
            }
        }

        result.addAll(tempExclusionList);

        tempGroupIdLine = null;
        tempArtifactIdLine = null;
        tempGroupIdLineNum = -1;
        tempArtifactIdLineNum = -1;
        tempDependencyList.clear();
        tempExclusionList.clear();
    }

}
