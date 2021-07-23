package org.txazo.framework.spring.boot.common;

public interface AutowiredBeanBuilder<A> {

    Object build(A autowired, Class<?> beanType);

}
