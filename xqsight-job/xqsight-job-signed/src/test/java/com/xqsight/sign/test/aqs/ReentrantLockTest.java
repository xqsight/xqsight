package com.xqsight.sign.test.aqs;

import sun.misc.Unsafe;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author ganggang.wang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ReentrantLockTest {

    private static final Unsafe unsafe = Unsafe.getUnsafe();

    public void test1(){
        ReentrantLock lock  = new ReentrantLock();
        lock.lock();
        unsafe.compareAndSwapInt(this,000,0,1);
    }
}
