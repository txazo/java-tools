package org.txazo.java.tools.mvc.repository;

import org.txazo.java.tools.mvc.common.DoDtoConverter;
import org.txazo.java.tools.mvc.common.Query;
import org.txazo.java.tools.mvc.mapper.BaseMapper;
import org.txazo.java.tools.mvc.result.PageResult;
import org.txazo.java.tools.mvc.util.PageUtil;

import java.util.List;

/**
 * @author tuxiaozhou
 * @date 2020/9/28
 */
public abstract class AbstractBaseRepository<Entity> implements BaseRepository<Entity> {

    /**
     * getMapper
     *
     * @return BaseMapper
     */
    protected abstract BaseMapper<Entity> getMapper();

    @Override
    public int add(Entity entity) {
        return getMapper().insert(entity);
    }

    @Override
    public int batchAdd(List<Entity> entityList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Entity entity) {
        return getMapper().update(entity);
    }

    @Override
    public int delete(Integer id) {
        return getMapper().delete(id);
    }

    @Override
    public Entity get(Integer id) {
        return getMapper().get(id);
    }

    @Override
    public List<Entity> getList(List<Integer> idList) {
        return getMapper().getList(idList);
    }

    @Override
    public PageResult<Entity> query(Query query) {
        PageUtil.check(query.getPageNum(), query.getPageSize());
        query.put("offset", PageUtil.offset(query.getPageNum(), query.getPageSize()));
        query.put("limit", query.getPageSize());
        int totalCount = getMapper().queryTotal(query);
        List<Entity> list = getMapper().query(query);
        return PageResult.newPageResult(totalCount, list, query.getPageNum(), query.getPageSize());
    }

}
