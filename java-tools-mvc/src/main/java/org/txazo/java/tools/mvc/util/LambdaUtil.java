package org.txazo.java.tools.mvc.util;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author tuxiaozhou
 * @date 2020/9/28
 */
public class LambdaUtil {

    /**
     * 集合映射
     */
    public static <S, D> List<D> map(Collection<S> collection, Function<? super S, ? extends D> mapper) {
        if (isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().map(mapper).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 集合映射
     */
    public static <S, D> List<D> map(Collection<S> collection, Function<? super S, ? extends D> mapper, boolean distinct) {
        if (!distinct) {
            return map(collection, mapper);
        }
        if (isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().map(mapper).filter(Objects::nonNull).distinct().collect(Collectors.toList());
    }

    /**
     * 集合映射
     */
    public static <S, D> List<D> map(Collection<S> collection, Predicate<? super S> filter, Function<? super S, ? extends D> mapper) {
        if (isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().filter(filter).map(mapper).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 集合映射
     */
    public static <S, D> List<D> map(Collection<S> collection, Predicate<? super S> filter, Function<? super S, ? extends D> mapper, boolean distinct) {
        if (!distinct) {
            return map(collection, filter, mapper);
        }
        if (isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().filter(filter).map(mapper).filter(Objects::nonNull).distinct().collect(Collectors.toList());
    }

    /**
     * 集合转换为Map
     */
    public static <K, V> Map<K, V> toMap(Collection<V> collection, Function<? super V, ? extends K> keyMapper) {
        if (isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream().collect(Collectors.toMap(keyMapper, v -> v, (v1, v2) -> v1));
    }

    /**
     * 集合转换为Map
     */
    public static <K, V> Map<K, V> toMap(Collection<V> collection, Predicate<? super V> filter, Function<? super V, ? extends K> keyMapper) {
        if (isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream().filter(filter).collect(Collectors.toMap(keyMapper, v -> v, (v1, v2) -> v1));
    }

    /**
     * 集合分组
     */
    public static <K, V> Map<K, List<V>> groupBy(Collection<V> collection, Function<? super V, ? extends K> classifier) {
        if (isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream().collect(Collectors.groupingBy(classifier));
    }

    private static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

}
