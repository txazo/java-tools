package org.txazo.java.tools.util.shell;

import java.io.File;

/**
 * @author tuxiaozhou
 * @date 2021/9/29
 */
public class ShellUtil {

    public static void runShell(String command, File dir, boolean showDir) throws Exception {
        Process process = Runtime.getRuntime().exec(new String[]{
                "/bin/sh",
                "-c",
                command
        }, null, dir);
        if (showDir) {
            System.out.println("$ > cd " + dir.getAbsolutePath());
        }
        System.out.println("$ > " + command);
        int status = process.waitFor();
        if (status != 0) {
            System.err.println("$ execute error");
        }
    }

}
