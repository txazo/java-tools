package org.txazo.java.tools.mvc.common;

import org.txazo.java.tools.mvc.util.BeanCopyUtil;
import org.txazo.java.tools.mvc.util.GenericUtil;
import org.txazo.java.tools.mvc.util.LambdaUtil;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author tuxiaozhou
 * @date 2020/9/28
 */
public class DoDtoConverter<DO, DTO> {

    private final Class<DO> doClassType;

    private final Class<DTO> dtoClassType;

    @SuppressWarnings("unchecked")
    public DoDtoConverter() {
        Type[] genericTypes = GenericUtil.getActualGenericClassesFromParent(this.getClass());
        doClassType = (Class<DO>) genericTypes[1];
        dtoClassType = (Class<DTO>) genericTypes[2];
    }

    public DTO convertDoToDto(DO d) {
        return BeanCopyUtil.copy(d, dtoClassType);
    }

    public DO convertDtoToDo(DTO dto) {
        return BeanCopyUtil.copy(dto, doClassType);
    }

    public List<DTO> convertDoListToDtoList(List<DO> doList) {
        return LambdaUtil.map(doList, this::convertDoToDto);
    }

    public List<DO> convertDtoListToDoList(List<DTO> dtoList) {
        return LambdaUtil.map(dtoList, this::convertDtoToDo);
    }

}
