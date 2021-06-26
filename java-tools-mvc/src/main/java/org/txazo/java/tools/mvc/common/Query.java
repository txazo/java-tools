package org.txazo.java.tools.mvc.common;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 通用查询条件
 *
 * @author tuxiaozhou
 * @date 2020/9/28
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Query extends HashMap<String, Object> implements Serializable {

    private List<OrderBy> orderByList;

    private Integer pageNum;

    private Integer pageSize;

    private static class OrderBy {

        private String field;

        private String order;

    }

}
