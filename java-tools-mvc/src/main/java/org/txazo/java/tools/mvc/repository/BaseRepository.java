package org.txazo.java.tools.mvc.repository;

import org.txazo.java.tools.mvc.common.Query;
import org.txazo.java.tools.mvc.result.PageResult;

import java.util.List;

/**
 * @author tuxiaozhou
 * @date 2020/9/28
 */
public interface BaseRepository<DTO> {

    int add(DTO dto);

    int batchAdd(List<DTO> dtoList);

    int update(DTO dto);

    int delete(Integer id);

    int delete(DTO dto);

    DTO get(Integer id);

    List<DTO> getList(List<Integer> idList);

    PageResult<DTO> query(Query query);

}
