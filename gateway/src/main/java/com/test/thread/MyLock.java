package com.test.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author: movie
 * @Date: 2021/3/25 12:27
 * 简单的lock不支持重入
 */

public class MyLock implements Lock {
    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.tryAcquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 1;
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                System.out.println(Thread.currentThread().getName() + "---" + getExclusiveOwnerThread().getName());
                throw new IllegalMonitorStateException();
            }

            boolean free = false;
            if (getState() - arg == 0) {
                setExclusiveOwnerThread(null);
                free = true;
            }
            setState(0);
            return free;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }
}
