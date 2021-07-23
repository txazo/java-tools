package org.txazo.framework.spring.boot.common;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class AutowiredFieldElement<A> extends InjectionMetadata.InjectedElement {

    private final Field field;
    private final A autowired;
    private final AutowiredBeanBuilder<A> builder;

    protected AutowiredFieldElement(Field field, A autowired, AutowiredBeanBuilder<A> builder) {
        super(field, null);
        this.field = field;
        this.autowired = autowired;
        this.builder = builder;
    }

    @Override
    protected void inject(Object bean, String beanName, PropertyValues pvs) throws Throwable {
        Class<?> autowiredType = field.getType();
        Object autowiredBean = builder.build(autowired, autowiredType);
        ReflectionUtils.makeAccessible(field);
        field.set(bean, autowiredBean);
    }

}
