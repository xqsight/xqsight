package com.xqsight.rxjava;

import io.reactivex.Flowable;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangganggang
 * @date 2017/03/31
 */
public class RxjavaTest {

    @Test
    public void test1() {
        List<String> list = Arrays.asList(
                "blue", "red", "green", "yellow", "orange", "cyan", "purple"
        );
        //Flowable.fromIterable(list).skip(2).subscribe(System.out::println);
        //Flowable.fromArray(list.toArray()).subscribe(System.out::println);
        //Flowable.just("blue").subscribe(System.out::println);
        // Flowable.range(0,100).subscribe(System.out::println);

    }

    @Test
    public void test2() {
        Flowable.just("hello word").subscribe(System.out::println);
    }

}
