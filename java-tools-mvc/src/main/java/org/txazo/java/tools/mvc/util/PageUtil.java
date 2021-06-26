package org.txazo.java.tools.mvc.util;

import com.google.common.base.Preconditions;

/**
 * @author tuxiaozhou
 * @date 2020/9/28
 */
public class PageUtil {

    public static void check(Integer pageNum, Integer pageSize) {
        Preconditions.checkNotNull(pageNum, "pageNum不能为空");
        Preconditions.checkArgument(pageNum > 0, "pageNum必须大于0");
        Preconditions.checkNotNull(pageSize, "pageSize不能为空");
        Preconditions.checkArgument(pageSize > 0, "pageSize必须大于0");
    }

    public static int offset(Integer pageNum, Integer pageSize) {
        return (pageNum - 1) * pageSize;
    }

}
