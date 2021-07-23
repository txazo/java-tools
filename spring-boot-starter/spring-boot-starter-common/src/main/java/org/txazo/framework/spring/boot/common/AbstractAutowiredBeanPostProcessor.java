package org.txazo.framework.spring.boot.common;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.springframework.core.BridgeMethodResolver.findBridgedMethod;
import static org.springframework.core.BridgeMethodResolver.isVisibilityBridgeMethodPair;
import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;
import static org.springframework.core.annotation.AnnotationUtils.getAnnotation;

/**
 * @author tuxiaozhou
 * @date 2021/7/23
 */
public abstract class AbstractAutowiredBeanPostProcessor<A extends Annotation> extends InstantiationAwareBeanPostProcessorAdapter
        implements MergedBeanDefinitionPostProcessor, PriorityOrdered, ApplicationContextAware, BeanClassLoaderAware,
        DisposableBean, AutowiredBeanBuilder<A> {

    private ApplicationContext applicationContext;

    private ClassLoader classLoader;

    protected final Class<A> annotationType;

    private final ConcurrentMap<String, InjectionMetadata> injectionMetadataCache = new ConcurrentHashMap<>(256);

    public AbstractAutowiredBeanPostProcessor(Class<A> annotationType) {
        this.annotationType = annotationType;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        InjectionMetadata metadata = findAutowiredMetadata(beanName, bean.getClass(), pvs);
        try {
            metadata.inject(bean, beanName, pvs);
        } catch (Throwable ex) {
            throw new BeanCreationException(beanName, "根据自定义注解注入bean失败", ex);
        }
        return pvs;
    }

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        if (beanType != null) {
            InjectionMetadata metadata = findAutowiredMetadata(beanName, beanType, null);
            metadata.checkConfigMembers(beanDefinition);
        }
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    private InjectionMetadata findAutowiredMetadata(String beanName, Class<?> clazz, PropertyValues pvs) {
        String cacheKey = (StringUtils.hasLength(beanName) ? beanName : clazz.getName());
        InjectionMetadata metadata = this.injectionMetadataCache.get(cacheKey);
        if (InjectionMetadata.needsRefresh(metadata, clazz)) {
            synchronized (this.injectionMetadataCache) {
                metadata = this.injectionMetadataCache.get(cacheKey);
                if (InjectionMetadata.needsRefresh(metadata, clazz)) {
                    if (metadata != null) {
                        metadata.clear(pvs);
                    }
                    try {
                        metadata = buildAutowiredMetadata(clazz);
                        this.injectionMetadataCache.put(cacheKey, metadata);
                    } catch (NoClassDefFoundError err) {
                        throw new IllegalStateException("Failed to introspect bean class [" + clazz.getName() + "] for autowired metadata: could not find class that it depends on", err);
                    }
                }
            }
        }
        return metadata;
    }

    private InjectionMetadata buildAutowiredMetadata(final Class<?> beanClass) {
        List<InjectionMetadata.InjectedElement> elements = new LinkedList<>();
        elements.addAll(findFieldAutowiredMetadata(beanClass));
        elements.addAll(findMethodAutowiredMetadata(beanClass));
        return new InjectionMetadata(beanClass, elements);
    }

    private List<InjectionMetadata.InjectedElement> findFieldAutowiredMetadata(final Class<?> beanClass) {
        final List<InjectionMetadata.InjectedElement> elements = new LinkedList<>();
        ReflectionUtils.doWithFields(beanClass, field -> {
            A autowired = getAnnotation(field, annotationType);
            if (autowired != null) {
                if (Modifier.isStatic(field.getModifiers())) {
                    // LOGGER.warn("@{} annotation is not supported on static fields: {}", annotationType, field);
                    return;
                }
                elements.add(new AutowiredFieldElement<>(field, autowired, this));
            }
        });
        return elements;
    }

    private List<InjectionMetadata.InjectedElement> findMethodAutowiredMetadata(final Class<?> beanClass) {
        final List<InjectionMetadata.InjectedElement> elements = new LinkedList<>();
        ReflectionUtils.doWithMethods(beanClass, method -> {
            Method bridgedMethod = findBridgedMethod(method);
            if (!isVisibilityBridgeMethodPair(method, bridgedMethod)) {
                return;
            }
            A autowired = findAnnotation(bridgedMethod, annotationType);
            if (autowired != null && method.equals(ClassUtils.getMostSpecificMethod(method, beanClass))) {
                if (Modifier.isStatic(method.getModifiers())) {
                    // LOGGER.warn("@{} annotation is not supported on static methods: {}", annotationType, method);
                    return;
                }
                if (method.getParameterTypes().length == 0) {
                    // LOGGER.warn("@{}  annotation should only be used on methods with parameters: {}", annotationType, method);
                }
                PropertyDescriptor pd = BeanUtils.findPropertyForMethod(bridgedMethod, beanClass);
                elements.add(new AutowiredMethodElement<>(method, pd, autowired, this));
            }
        });
        return elements;
    }

}
