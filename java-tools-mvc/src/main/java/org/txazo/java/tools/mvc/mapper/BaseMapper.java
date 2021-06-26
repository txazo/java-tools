package org.txazo.java.tools.mvc.mapper;

import org.txazo.java.tools.mvc.common.Query;

import java.util.List;

/**
 * 通用增删改查操作
 *
 * @author tuxiaozhou
 * @date 2020/9/28
 */
public interface BaseMapper<T> {

    int insert(T t);

    int batchInsert(List<T> list);

    int update(T t);

    int delete(Integer id);

    int delete(T t);

    T get(Integer id);

    List<T> getList(List<Integer> idList);

    List<T> query(Query query);

    int queryTotal(Query query);

}
