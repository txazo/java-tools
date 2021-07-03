package org.txazo.java.tools.hug.move.util;

import java.io.File;

/**
 * @author tuxiaozhou
 * @date 2021/7/2
 */
public class ShellUtil {

    public static void runShell(String command, File dir, boolean showDir) throws Exception {
        Process process = Runtime.getRuntime().exec(new String[]{
                "/bin/sh",
                "-c",
                command
        }, null, dir);
        if (showDir) {
            System.out.println("shell > cd " + dir.getAbsolutePath());
        }
        System.out.println("shell > " + command);
        int status = process.waitFor();
        if (status != 0) {
            System.err.println("shell 执行失败: " + command + " " + dir.getAbsolutePath());
        }
    }

}
