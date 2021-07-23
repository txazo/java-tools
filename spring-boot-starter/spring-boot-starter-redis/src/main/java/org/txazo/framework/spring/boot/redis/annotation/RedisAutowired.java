package org.txazo.framework.spring.boot.redis.annotation;

import java.lang.annotation.*;

/**
 * @author tuxiaozhou
 * @date 2021/7/23
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisAutowired {

    String value() default "";

}
