package com.yjxxt.ems.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.METHOD})
@Retention(RUNTIME)
@Documented
public @interface RequirePermission {
    String code() default "";
}
