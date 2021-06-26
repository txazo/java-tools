package org.txazo.java.tools.mvc.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.txazo.java.tools.mvc.util.PageUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用查询条件
 *
 * @author tuxiaozhou
 * @date 2020/9/28
 */
@Data
@NoArgsConstructor
public class Query implements Serializable {

    public static final String ORDER_ASC = "asc";
    public static final String ORDER_DESC = "desc";

    private Map<String, Object> whereMap = Maps.newHashMap();

    private Map<String, Object> whereSqlMap = Maps.newHashMap();

    private List<OrderBy> orderByList;

    private Integer offset;

    private Integer limit;

    public static Query newBuilder() {
        return new Query();
    }

    public Query page(Integer pageNum, Integer pageSize) {
        PageUtil.check(pageNum, pageSize);
        this.limit = pageSize;
        this.offset = PageUtil.offset(pageNum, pageSize);
        return this;
    }

    public Query whereEquals(String fieldName, Object fieldValue) {
        whereMap.put(fieldName, fieldValue);
        return this;
    }

    public Query whereSql(String sql, Object param) {
        whereSqlMap.put(sql, param);
        return this;
    }

    public Query orderBy(String field, String order) {
        if (orderByList == null) {
            orderByList = Lists.newArrayList();
        }
        orderByList.add(new OrderBy(field, order));
        return this;
    }

    @Data
    private static class OrderBy {

        private String field;

        private String order;

        public OrderBy(String field, String order) {
            this.field = field;
            this.order = order;
        }

    }

}
