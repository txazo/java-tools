package org.txazo.java.tools.mvc.repository;

import org.txazo.java.tools.mvc.common.Query;
import org.txazo.java.tools.mvc.mapper.BaseMapper;
import org.txazo.java.tools.mvc.result.PageResult;

import java.util.Collections;
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
    public int delete(Long id) {
        return getMapper().delete(id);
    }

    @Override
    public Entity get(Long id) {
        return getMapper().get(id);
    }

    @Override
    public List<Entity> getList(List<Long> idList) {
        return getMapper().getList(idList);
    }

    @Override
    public PageResult<Entity> query(Query query) {
        int totalCount = getMapper().queryTotal(query);
        if (totalCount == 0) {
            return PageResult.newPageResult(totalCount, Collections.emptyList(), false);
        }
        List<Entity> list = getMapper().query(query);
        return PageResult.newPageResult(totalCount, list, query);
    }

}
