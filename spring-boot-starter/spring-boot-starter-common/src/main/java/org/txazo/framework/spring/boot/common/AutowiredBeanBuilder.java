package org.txazo.framework.spring.boot.common;

/**
 * @author tuxiaozhou
 * @date 2021/7/23
 */
public interface AutowiredBeanBuilder<A> {

    /**
     * 构建注入bean
     *
     * @param autowired 注解
     * @param beanType  注入bean类型
     * @return bean
     */
    Object build(A autowired, Class<?> beanType);

}
