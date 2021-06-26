package org.txazo.java.tools.mvc.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author tuxiaozhou
 * @date 2020/9/28
 */
public class GenericUtil {

    public static Type[] getActualGenericClassesFromInterface(Class<?> classType) {
        return ((ParameterizedType) classType.getGenericInterfaces()[0]).getActualTypeArguments();
    }

    public static Class<?> getActualGenericClassesFromInterface(Class<?> classType, int index) {
        return (Class<?>) getActualGenericClassesFromInterface(classType)[index];
    }

    public static Type[] getActualGenericClassesFromParent(Class<?> classType) {
        return ((ParameterizedType) classType.getGenericSuperclass()).getActualTypeArguments();
    }

    public static Class<?> getActualGenericClassesFromParent(Class<?> classType, int index) {
        return (Class<?>) getActualGenericClassesFromParent(classType)[index];
    }

}
