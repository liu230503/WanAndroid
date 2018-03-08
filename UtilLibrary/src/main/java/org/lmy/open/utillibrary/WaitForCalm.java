package org.lmy.open.utillibrary;

import android.os.Handler;
import android.os.SystemClock;

/**********************************************************************
 *
 *
 * @类名 WaitForCalm
 * @包名 org.lmy.open.utillibrary
 * @author lmy
 * @创建日期 2018/3/8
 ***********************************************************************/
public class WaitForCalm implements Runnable {
    private long lastActivityTiem = -1;
    private Handler handler;
    private boolean isEnable = true;
    private boolean isNextTimeOut = true;
    private long waitTime;
    private OnJobListener onJobListener;
    private Handler uiHandler;
    private NextRunnable nextRunnable = new NextRunnable();
    private OnJobRunnable onJobRunnable = new OnJobRunnable();
    //特别延长时间，针对延时时间明显不同的操作
    private long specialWaitTime = 5 * 1000 * 60;
    private boolean startSpecialWaitTime = false;//是否启动特别延时的时间。

    /**
     * Constructor
     *
     * @param waitTime 等待时长
     */
    public WaitForCalm(long waitTime) {
        handler = OutOfUiThreadUtil.getOrCreateThread(OutOfUiThreadsKey.DEFAULT_OUT_OF_UITHREA).getAndWaitForHandler();
        uiHandler = new Handler();
        this.waitTime = waitTime;
    }

    /**
     * Constructor
     *
     * @param waitTime
     */
    public WaitForCalm(long waitTime, Handler handler) {
        this.handler = OutOfUiThreadUtil.getOrCreateThread(OutOfUiThreadsKey.DEFAULT_OUT_OF_UITHREA)
                .getAndWaitForHandler();
        uiHandler = handler;
        this.waitTime = waitTime;
    }

    public void changeWaitTime(long waitTime) {
        reset();
        this.waitTime = waitTime;
        if (isEnable) {
            activity();
        }
    }

    /**
     * @param isEnable
     */
    public void setWaitForCalmEnabled(boolean isEnable) {
        if (this.isEnable == isEnable) {
            return;
        }
        this.isEnable = isEnable;
        if (isEnable) {

        } else {
            reset();
        }
    }

    /**
     * @return
     */
    public boolean isEnabled() {
        return isEnable;
    }

    public void reset() {
        if (handler == null)
            return;
        isNextTimeOut = true;
        handler.removeCallbacks(this);
        // add by lijiachun@frogshealth.com 2017/8/16 防止空指针异常.
        if (uiHandler != null) {
            uiHandler.removeCallbacks(nextRunnable);
            uiHandler.removeCallbacks(onJobRunnable);
        }
        lastActivityTiem = -1;
    }

    public void destory() {
        if (handler == null)
            return;
        isEnable = false;
        handler.removeCallbacks(nextRunnable);
        handler.removeCallbacks(this);
        handler = null;
        uiHandler.removeCallbacks(onJobRunnable);
        uiHandler = null;
    }

    /**
     *
     */
    public void startNext() {
        activity();
    }

    /**
     *
     */
    public void activity() {
        if (!isEnable) {
            return;
        }
        // if (handler == null)
        // return;
        lastActivityTiem = SystemClock.elapsedRealtime();
        if (isNextTimeOut && isEnable) {
            // add by lijiachun@frogshealth.com 2017/8/16 防止空指针异常.
            if (handler != null) {
                handler.removeCallbacks(nextRunnable);
                handler.post(nextRunnable);
            }
        }
    }

    private class NextRunnable implements Runnable {

        @Override
        public void run() {
            setNext();
        }

    }

    /**
     *
     */
    private void setNext() {
        if (handler == null)
            return;
        if (isNextTimeOut && isEnable) {
            // calc next time
            isNextTimeOut = false;
            long nextUptimeMillis = SystemClock.elapsedRealtime() - lastActivityTiem;
            long next = 0;
            if (startSpecialWaitTime) {
                next = specialWaitTime - Math.abs(nextUptimeMillis);
            } else {
                next = waitTime - Math.abs(nextUptimeMillis);
            }
            handler.removeCallbacks(this);
            if (next > 0) {
                handler.postDelayed(this, next);
            } else {
                handler.post(this);
            }
        }
    }


    /**
     * (non-Javadoc)
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        isNextTimeOut = true;
        if (lastActivityTiem == -1)
            return;
        if (uiHandler == null)
            return;
        // To check
        long nextUptimeMillis = SystemClock.elapsedRealtime() - lastActivityTiem;
        long next = 0;
        if (startSpecialWaitTime) {
            next = Math.abs(nextUptimeMillis) - specialWaitTime;
        } else {
            next = Math.abs(nextUptimeMillis) - waitTime;
        }
        if (next >= 0) { // OK to Exce
            lastActivityTiem = -1;
            uiHandler.post(onJobRunnable);
        } else {// set next
            setNext();
        }
        startSpecialWaitTime = false;
    }

    /**
     * The class <code>OnJobRunnable</code>
     *
     * @author Administrator
     * @version 1.0
     */
    private class OnJobRunnable implements Runnable {

        @Override
        public void run() {
            onJob();
        }

    }

    /**
     *
     */
    private void onJob() {
        if (handler == null)
            return;
        if (!isEnable) {
            return;
        }
        if (onJobListener != null)
            onJobListener.onJob();
    }

    public OnJobListener getOnJobListener() {
        return onJobListener;
    }

    public void setOnJobListener(OnJobListener onJobListener) {
        this.onJobListener = onJobListener;
    }

    public interface OnJobListener {
        public void onJob();
    }

    public void setSpecialWaitTime(long specialWaitTime) {
        this.specialWaitTime = specialWaitTime;
        startSpecialWaitTime = true;
    }


}
