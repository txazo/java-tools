package org.txazo.framework.spring.boot.common;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author tuxiaozhou
 * @date 2021/7/23
 */
public class AutowiredMethodElement<A> extends InjectionMetadata.InjectedElement {

    private final Method method;
    private final A autowired;
    private final AutowiredBeanBuilder<A> builder;

    protected AutowiredMethodElement(Method method, PropertyDescriptor pd, A autowired, AutowiredBeanBuilder<A> builder) {
        super(method, pd);
        this.method = method;
        this.autowired = autowired;
        this.builder = builder;
    }

    @Override
    protected void inject(Object bean, String beanName, PropertyValues pvs) throws Throwable {
        Class<?> autowiredClass = pd.getPropertyType();
        Object autowiredBean = builder.build(autowired, autowiredClass);
        ReflectionUtils.makeAccessible(method);
        method.invoke(bean, autowiredBean);
    }

}
