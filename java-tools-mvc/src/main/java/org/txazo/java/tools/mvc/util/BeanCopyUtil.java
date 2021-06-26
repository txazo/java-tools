package org.txazo.java.tools.mvc.util;

import org.springframework.beans.BeanUtils;

import java.util.function.BiConsumer;

/**
 * @author tuxiaozhou
 * @date 2020/9/28
 */
public class BeanCopyUtil {

    public static <S, D> D copy(S source, Class<D> destClassType) {
        return copy(source, destClassType, null);
    }

    public static <S, D> D copy(S source, Class<D> destClassType, BiConsumer<S, D> consumer) {
        if (source == null) {
            return null;
        }
        try {
            D dest = destClassType.newInstance();
            BeanUtils.copyProperties(source, dest);
            if (consumer != null) {
                consumer.accept(source, dest);
            }
            return dest;
        } catch (Exception e) {
            throw new RuntimeException(String.format("BeanCopyUtil copy error sourceType=%s, destType=%s", source.getClass().getName(), destClassType.getName()), e);
        }
    }

}
