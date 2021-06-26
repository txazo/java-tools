package org.txazo.java.tools.mvc.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author tuxiaozhou
 * @date 2020/9/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {

    private Integer totalCount;

    private List<T> list;

    private boolean hasNext;

    public static <T> PageResult<T> newPageResult(Integer totalCount, List<T> list, Integer pageNum, Integer pageSize) {
        return new PageResult<>(totalCount, list, pageNum * pageSize < totalCount);
    }

    public static <T> PageResult<T> newPageResult(List<T> list, Integer pageSize) {
        return new PageResult<>(null, list, list != null && list.size() >= pageSize);
    }

}
