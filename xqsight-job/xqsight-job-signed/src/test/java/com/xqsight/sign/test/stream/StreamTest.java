package com.xqsight.sign.test.stream;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author ganggang.wang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class StreamTest {

    /**
     * 斐波纳契 数组
     */
    @Test
    public void test1(){
        Stream.iterate(new int[]{0, 1},t -> new int[]{t[1], t[0]+t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));
    }
}
