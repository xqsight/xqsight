package com.xqsight.security.annontation;

import java.lang.annotation.*;

/**
 * @author wangganggang
 * @date 2017年07月24日 11:01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WriteLog {

    String value() default "";
}