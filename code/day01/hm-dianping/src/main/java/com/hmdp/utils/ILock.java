package com.hmdp.utils;

public interface ILock {

    //尝试获取锁
    //timeoutSec 锁持有的超时时间，过期后自动释放
    //return true or false
    boolean tryLock(long timeoutSec);

    //释放锁
    void unlock();

}
