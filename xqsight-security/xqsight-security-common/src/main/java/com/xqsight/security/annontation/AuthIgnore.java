package com.xqsight.security.annontation;

import java.lang.annotation.*;

/**
 * @author wangganggang
 * @date 2017年07月21日 ${time}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {
}
