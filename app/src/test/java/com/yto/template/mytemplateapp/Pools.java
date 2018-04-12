package com.yto.template.mytemplateapp;

/**
 * Created by Chris on 2018/4/9.
 */

public class Pools {
    /**
     * 46     * Interface for managing a pool of objects.
     * 47     *
     * 48     * @param <T> The pooled type.
     * 49
     */
    public static interface Pool<T> {

        /**
         * 53         * @return An instance from the pool if such, null otherwise.
         * 54
         */
        public T acquire();

        /**
         * 58         * Release an instance to the pool.
         * 59         *
         * 60         * @param instance The instance to release.
         * 61         * @return Whether the instance was put in the pool.
         * 62         *
         * 63         * @throws IllegalStateException If the instance is already in the pool.
         * 64
         */
        public boolean release(T instance);
    }

    private Pools() {
                /* do nothing - hiding constructor */
    }

    /**
     * 73     * Simple (non-synchronized) pool of objects.
     * 74     *
     * 75     * @param <T> The pooled type.
     * 76
     */
    public static class SimplePool<T> implements Pool<T> {
        private final Object[] mPool;

        private int mPoolSize;

        /**
         * 83         * Creates a new instance.
         * 84         *
         * 85         * @param maxPoolSize The max pool size.
         * 86         *
         * 87         * @throws IllegalArgumentException If the max pool size is less than zero.
         * 88
         */
        public SimplePool(int maxPoolSize) {
            if (maxPoolSize <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            mPool = new Object[maxPoolSize];
        }

        @Override
        @SuppressWarnings("unchecked")
        public T acquire() {
            if (mPoolSize > 0) {
                final int lastPooledIndex = mPoolSize - 1;
                T instance = (T) mPool[lastPooledIndex];
                mPool[lastPooledIndex] = null;
                mPoolSize--;
                return instance;
            }
            return null;
        }

        @Override
        public boolean release(T instance) {
            if (isInPool(instance)) {
                throw new IllegalStateException("Already in the pool!");
            }
            if (mPoolSize < mPool.length) {
                mPool[mPoolSize] = instance;
                mPoolSize++;
                return true;
            }
            return false;
        }

        private boolean isInPool(T instance) {
            for (int i = 0; i < mPoolSize; i++) {
                if (mPool[i] == instance) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 133     * Synchronized) pool of objects.
     * 134     *
     * 135     * @param <T> The pooled type.
     * 136
     */
    public static class SynchronizedPool<T> extends SimplePool<T> {
        private final Object mLock = new Object();

        /**
         * 141         * Creates a new instance.
         * 142         *
         * 143         * @param maxPoolSize The max pool size.
         * 144         *
         * 145         * @throws IllegalArgumentException If the max pool size is less than zero.
         * 146
         */
        public SynchronizedPool(int maxPoolSize) {
            super(maxPoolSize);
        }

        @Override
        public T acquire() {
            synchronized (mLock) {
                return super.acquire();
            }
        }

        @Override
        public boolean release(T element) {
            synchronized (mLock) {
                return super.release(element);
            }
        }
    }
}
