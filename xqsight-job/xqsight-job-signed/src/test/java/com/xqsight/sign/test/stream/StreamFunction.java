package com.xqsight.sign.test.stream;


import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author ganggang.wang
 * @see [相关类/方法]（可选）
 * @since 2020/4/29
 */
public class StreamFunction {

    public void testFun(){
        Thread thread = new Thread(()->{
            System.out.println("1");
        });
    }

    public void testStream(){
        Stream.of(1,2,3,4,5,6).map(x->"ww" + x)
                .collect(Collectors.toList());

        String o = Optional.ofNullable("").orElse("1");

        CompletableFuture.runAsync(()-> System.out.println(""));


    }


}
