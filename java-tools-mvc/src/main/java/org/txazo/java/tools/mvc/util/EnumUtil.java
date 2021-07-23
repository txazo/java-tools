package org.txazo.java.tools.mvc.util;

import java.util.Objects;
import java.util.function.Function;

/**
 * 枚举工具类
 *
 * @author tuxiaozhou
 * @date 2021/7/22
 */
public class EnumUtil {

    public static <K, E extends Enum<E>> E getEnum(Class<E> enumClassType, K k, Function<E, K> function) {
        if (!enumClassType.isEnum()) {
            throw new UnsupportedOperationException("Not enum class " + enumClassType.getName());
        }
        E[] enumArray = enumClassType.getEnumConstants();
        for (E e : enumArray) {
            if (Objects.equals(k, function.apply(e))) {
                return e;
            }
        }
        return null;
    }

}
