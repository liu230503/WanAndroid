package org.lmy.open.utillibrary;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.nostra13.universalimageloader.core.assist.deque.LIFOLinkedBlockingDeque;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**********************************************************************
 *
 *
 * @类名 OutOfUiThreadUtil
 * @包名 org.lmy.open.utillibrary
 * @author lmy
 * @创建日期 2018/3/8
 ***********************************************************************/
public class OutOfUiThreadUtil {private static Map<String, OutOfUiLooperThread> mOutOfUiLooperThreadMap = new HashMap<String, OutOfUiLooperThread>();
    private final static int MAX_SIZE = 20;

    public static synchronized OutOfUiLooperThread getOrCreateThread(String key) {
        OutOfUiLooperThread thread = mOutOfUiLooperThreadMap.get(key);
        if (thread == null) {
            thread = new OutOfUiLooperThread(key, android.os.Process.THREAD_PRIORITY_LOWEST);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
            mOutOfUiLooperThreadMap.put(key, thread);
            if (mOutOfUiLooperThreadMap.size() >= MAX_SIZE) {
                // 界限最多线程池 actually there can be 300.
                throw new RuntimeException("Don't to create too much OutOfUiLooperThread!");
            }
        }
        return thread;
    }

    public static synchronized boolean destoryThread(String key) {
        OutOfUiLooperThread thread = mOutOfUiLooperThreadMap.get(key);
        if (thread != null) {
            thread.quit();
            mOutOfUiLooperThreadMap.remove(key);
            return true;
        }
        return false;
    }

    /**
     *
     * The class <code>LooperThread</code> <br>
     * 不可过多存在，否则程序A/Looper(4946): Could not create epoll instance. errno=24 异常
     *
     * @author elc_zhangfei
     * @version 1.0
     */
    public static class OutOfUiLooperThread extends HandlerThread implements Handler.Callback {

        /**
         * Constructor
         *
         * @param pos
         */
        public OutOfUiLooperThread(String name) {
            super(name);
        }

        /**
         * Constructor
         *
         * @param pos
         */
        public OutOfUiLooperThread(String name, int priority) {
            super(name, priority);
        }

        private Handler mOutuiHandler;
        private Handler.Callback mCallback;

        public Handler getAndWaitForHandler() {
            // getLooper内部已有判断等候
            if (mOutuiHandler == null) {
                mOutuiHandler = new Handler(this.getLooper(), this);
            }
            return mOutuiHandler;
        }

        /**
         * (non-Javadoc)
         *
         * @see android.os.Handler.Callback#handleMessage(android.os.Message)
         */
        @Override
        public boolean handleMessage(Message msg) {
            if (mCallback != null)
                return mCallback.handleMessage(msg);
            return false;
        }

        public Handler.Callback getCallback() {
            return mCallback;
        }

        public void setCallback(Handler.Callback mCallback) {
            this.mCallback = mCallback;
        }

    }

    private static Map<String, Executor> mExecutorMap = new HashMap<String, Executor>();

    public void post(String poolKey, Runnable runnable) {
        Executor executor = getExecutor(poolKey);
        executor.execute(runnable);
    }

    public static synchronized Executor getExecutor(String key) {
        Executor executor = mExecutorMap.get(key);
        if (executor == null) {
            executor = createDefaultExecutor(key);
            mExecutorMap.put(key, executor);
            if (mExecutorMap.size() >= MAX_SIZE) {
                throw new RuntimeException("Don't to create too much ExecutorMap!");
            }
        }
        return executor;
    }

    private static Executor createDefaultExecutor(String poolKey) {
        Executor executor = createCustomExecutor(poolKey);
        if (executor == null)
            executor = createExecutor(poolKey, 0, 1, 0L, Thread.MIN_PRIORITY, QueueProcessingType.FIFO);
        mExecutorMap.put(poolKey, executor);
        return executor;
    }

    private static Executor createCustomExecutor(String poolKey) {
        switch (poolKey) {
            case BaseOutOfUiThreadsKey.EXECUTOR_NETWORK:
                return createExecutor(poolKey, 0, 3, 1000 * 10L, Thread.MIN_PRIORITY, QueueProcessingType.FIFO);
            case BaseOutOfUiThreadsKey.EXECUTOR_SERVICES:
                return createExecutor(poolKey, 2, 3, 1000 * 10L, Thread.MIN_PRIORITY, QueueProcessingType.FIFO);
            case BaseOutOfUiThreadsKey.EXECUTOR_CLIENT_OUTTER:
                return createExecutor(poolKey, 0, 2, 1000 * 10L, Thread.MIN_PRIORITY, QueueProcessingType.FIFO);
            case OutOfUiThreadsKey.EXECUTOR_FRAME_RESET:
                return createExecutor(poolKey, 0, 2, 1000 * 10L, Thread.MIN_PRIORITY, QueueProcessingType.FIFO);
            default:
                break;
        }
        return null;
    }

    public static Executor createExecutorAndCache(String poolKey, int threadPoolSize) {
        Executor executor = createExecutorAndCache(poolKey, 0, threadPoolSize, 0L, Thread.MIN_PRIORITY,
                QueueProcessingType.FIFO);
        return executor;
    }

    public static Executor createExecutorAndCache(String poolKey, int corePoolSize, int threadPoolSize,
                                                  long keepAliveTime, int threadPriority, QueueProcessingType tasksProcessingType) {
        Executor executor = createExecutor(poolKey, corePoolSize, threadPoolSize, keepAliveTime, threadPriority,
                tasksProcessingType);
        mExecutorMap.put(poolKey, executor);
        return executor;
    }

    public static Executor createExecutor(String poolKey, int threadPoolSize) {
        Executor executor = createExecutor(poolKey, 0, threadPoolSize, 0L, Thread.MIN_PRIORITY,
                QueueProcessingType.FIFO);
        return executor;
    }

    public static Executor createExecutor(String poolKey, int corePoolSize, int threadPoolSize, long keepAliveTime,
                                          int threadPriority, QueueProcessingType tasksProcessingType) {
        boolean lifo = tasksProcessingType == QueueProcessingType.LIFO;
        BlockingQueue<Runnable> taskQueue = lifo ? new LIFOLinkedBlockingDeque<Runnable>()
                : new LinkedBlockingQueue<Runnable>();
        return new ThreadPoolExecutor(corePoolSize, threadPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, taskQueue,
                createThreadFactory(poolKey, threadPriority));
    }

    public enum QueueProcessingType {
        FIFO, LIFO
    }

    /** Creates default implementation of {@linkplain ThreadFactory thread factory} for task executor */
    private static ThreadFactory createThreadFactory(String poolName, int threadPriority) {
        return new DefaultThreadFactory(poolName, threadPriority);
    }

    private static class DefaultThreadFactory implements ThreadFactory {

        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        private final int threadPriority;
        private final String poolName;

        DefaultThreadFactory(String poolName, int threadPriority) {
            this.threadPriority = threadPriority;
            this.poolName = poolName;
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = poolName + "_";
        }

        public Thread newThread(Runnable r) {
            AndroidThread t = new AndroidThread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (threadPriority == Thread.NORM_PRIORITY) {
                t.setAnroidOsPriority(android.os.Process.THREAD_PRIORITY_DEFAULT);
            } else if (threadPriority < Thread.NORM_PRIORITY) {
                // Thread.MIN_PRIORITY
                t.setAnroidOsPriority(android.os.Process.THREAD_PRIORITY_LOWEST);
            } else {
                // Thread.MAX_PRIORITY
                t.setAnroidOsPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);
            }
            t.setPriority(threadPriority);
            return t;
        }

    }

    private static class AndroidThread extends Thread {
        // 该级别的优先级效果明显
        private int mAndroidOsPriority = android.os.Process.THREAD_PRIORITY_DEFAULT;
        private int mLoopCount = 0;

        public AndroidThread() {
            super();
        }

        public AndroidThread(Runnable runnable, String threadName) {
            super(runnable, threadName);
        }

        public AndroidThread(Runnable runnable) {
            super(runnable);
        }

        public AndroidThread(String threadName) {
            super(threadName);
        }

        public AndroidThread(ThreadGroup group, Runnable runnable, String threadName, long stackSize) {
            super(group, runnable, threadName, stackSize);
        }

        public AndroidThread(ThreadGroup group, Runnable runnable, String threadName) {
            super(group, runnable, threadName);
        }

        public AndroidThread(ThreadGroup group, Runnable runnable) {
            super(group, runnable);
        }

        public AndroidThread(ThreadGroup group, String threadName) {
            super(group, threadName);
        }

        public void setAnroidOsPriority(int p) {
            mAndroidOsPriority = p;
        }

        @Override
        public void run() {
            android.os.Process.setThreadPriority(mAndroidOsPriority);
            super.run();
        }
    }
}