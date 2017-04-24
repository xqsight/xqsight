package com.test;

import org.junit.Test;

/**
 * @author wangganggang
 * @date 2017/04/21
 */

public class ExceptionTest {

    @Test
    public void test(){
        int i =0x7ffffffe;
        while (true){
            i++;
        }
    }
}
