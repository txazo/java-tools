package org.txazo.java.tools.mvc.mapper;

import org.apache.ibatis.annotations.Param;
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

    // int batchInsert(@Param("list") List<T> list);

    int update(T t);

    int delete(@Param("id") Long id);

    T get(@Param("id") Long id);

    List<T> getList(@Param("idList") List<Long> idList);

    List<T> query(Query query);

    int queryTotal(Query query);

}
