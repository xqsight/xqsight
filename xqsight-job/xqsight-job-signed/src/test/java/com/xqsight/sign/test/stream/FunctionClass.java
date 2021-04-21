package com.xqsight.sign.test.stream;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author ganggang.wang
 * @see [相关类/方法]（可选）
 * @since 2020/4/30
 */
@FunctionalInterface
public interface FunctionClass {

    void fun1();

    default void fun2(){
        System.out.println("1");
    }

}
