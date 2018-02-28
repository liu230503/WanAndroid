package org.lmy.open.utillibrary;

import android.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**********************************************************************
 *
 *
 * @类名 LoggerUtil
 * @包名 org.lmy.open.utillibrary
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public class LoggerUtil {
    /**
     * Logger
     */
    private static Logger DEFAULT_LOGGER_INTERIO;
    private static boolean DEBUG = false;
    private static String TAG = "LOGUTIL-DEFAULT";

    public static void initLogger(String name, boolean isDebug) {
        DEBUG = isDebug;
        DEFAULT_LOGGER_INTERIO = LoggerFactory.getLogger(name + (isDebug ? "_DEBUG" : ""));
        DEFAULT_LOGGER = DEFAULT_LOGGER_INTERIO;
    }

    public static Logger DEFAULT_LOGGER = new Logger() {

        @Override
        public void warn(Marker arg0, String arg1, Object arg2, Object arg3) {
            if (DEFAULT_LOGGER_INTERIO != null) {
                DEFAULT_LOGGER_INTERIO.warn(arg0, arg1, arg2, arg3);
            } else {
                Log.w(TAG, arg1);
            }

        }

        @Override
        public void warn(Marker arg0, String arg1, Throwable arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.warn(arg0, arg1, arg2);
            else
                Log.w(TAG, arg1, arg2);
        }

        @Override
        public void warn(Marker arg0, String arg1, Object... arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.warn(arg0, arg1, arg2);
            else
                Log.w(TAG, arg1);
        }

        @Override
        public void warn(Marker arg0, String arg1, Object arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.warn(arg0, arg1, arg2);
            else
                Log.w(TAG, arg1);
        }

        @Override
        public void warn(String arg0, Object arg1, Object arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.warn(arg0, arg1, arg2);
            else
                Log.w(TAG, arg0);
        }

        @Override
        public void warn(Marker arg0, String arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.warn(arg0, arg1);
            else
                Log.w(TAG, arg1);
        }

        @Override
        public void warn(String arg0, Throwable arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.warn(arg0, arg1);
            else
                Log.w(TAG, arg0, arg1);
        }

        @Override
        public void warn(String arg0, Object... arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.warn(arg0, arg1);
            else
                Log.w(TAG, arg0);
        }

        @Override
        public void warn(String arg0, Object arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.warn(arg0, arg1);
            else
                Log.w(TAG, arg0);
        }

        @Override
        public void warn(String arg0) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.warn(arg0);
            else
                Log.w(TAG, arg0);
        }

        @Override
        public void trace(Marker arg0, String arg1, Object arg2, Object arg3) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.trace(arg0, arg1, arg2, arg3);
            else
                Log.v(TAG, arg1);
        }

        @Override
        public void trace(Marker arg0, String arg1, Throwable arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.trace(arg0, arg1, arg2);
            else
                Log.v(TAG, arg1, arg2);
        }

        @Override
        public void trace(Marker arg0, String arg1, Object... arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.trace(arg0, arg1, arg2);
            else
                Log.v(TAG, arg1);
        }

        @Override
        public void trace(Marker arg0, String arg1, Object arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.trace(arg0, arg1, arg2);
            else
                Log.v(TAG, arg1);
        }

        @Override
        public void trace(String arg0, Object arg1, Object arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.trace(arg0, arg1, arg2);
            else
                Log.v(TAG, arg0);
        }

        @Override
        public void trace(Marker arg0, String arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.trace(arg0, arg1);
            else
                Log.v(TAG, arg1);
        }

        @Override
        public void trace(String arg0, Throwable arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.trace(arg0, arg1);
            else
                Log.v(TAG, arg0);
        }

        @Override
        public void trace(String arg0, Object... arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.trace(arg0, arg1);
            else
                Log.v(TAG, arg0);
        }

        @Override
        public void trace(String arg0, Object arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.trace(arg0, arg1);
            else
                Log.v(TAG, arg0);
        }

        @Override
        public void trace(String arg0) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.trace(arg0);
            else
                Log.v(TAG, arg0);
        }

        @Override
        public boolean isWarnEnabled(Marker arg0) {
            if (DEFAULT_LOGGER_INTERIO != null)
                return DEFAULT_LOGGER_INTERIO.isWarnEnabled(arg0);
            return false;
        }

        @Override
        public boolean isWarnEnabled() {
            if (DEFAULT_LOGGER_INTERIO != null)
                return DEFAULT_LOGGER_INTERIO.isWarnEnabled();
            return false;
        }

        @Override
        public boolean isTraceEnabled(Marker arg0) {
            if (DEFAULT_LOGGER_INTERIO != null)
                return DEFAULT_LOGGER_INTERIO.isTraceEnabled();
            return false;
        }

        @Override
        public boolean isTraceEnabled() {
            if (DEFAULT_LOGGER_INTERIO != null)
                return DEFAULT_LOGGER_INTERIO.isTraceEnabled();
            return false;
        }

        @Override
        public boolean isInfoEnabled(Marker arg0) {
            if (DEFAULT_LOGGER_INTERIO != null)
                return DEFAULT_LOGGER_INTERIO.isInfoEnabled(arg0);
            return false;
        }

        @Override
        public boolean isInfoEnabled() {
            if (DEFAULT_LOGGER_INTERIO != null)
                return DEFAULT_LOGGER_INTERIO.isInfoEnabled();
            return false;
        }

        @Override
        public boolean isErrorEnabled(Marker arg0) {
            if (DEFAULT_LOGGER_INTERIO != null)
                return DEFAULT_LOGGER_INTERIO.isErrorEnabled(arg0);
            return false;
        }

        @Override
        public boolean isErrorEnabled() {
            if (DEFAULT_LOGGER_INTERIO != null)
                return DEFAULT_LOGGER_INTERIO.isErrorEnabled();
            return false;
        }

        @Override
        public boolean isDebugEnabled(Marker arg0) {
            if (DEFAULT_LOGGER_INTERIO != null)
                return DEFAULT_LOGGER_INTERIO.isDebugEnabled(arg0);
            return false;
        }

        @Override
        public boolean isDebugEnabled() {
            if (DEFAULT_LOGGER_INTERIO != null)
                return DEFAULT_LOGGER_INTERIO.isDebugEnabled();
            return false;
        }

        @Override
        public void info(Marker arg0, String arg1, Object arg2, Object arg3) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.info(arg0, arg1, arg2);
            else
                Log.i(TAG, arg1);
        }

        @Override
        public void info(Marker arg0, String arg1, Throwable arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.info(arg0, arg1, arg2);
            else
                Log.i(TAG, arg1);
        }

        @Override
        public void info(Marker arg0, String arg1, Object... arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.info(arg0, arg1, arg2);
            else
                Log.i(TAG, arg1);
        }

        @Override
        public void info(Marker arg0, String arg1, Object arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.info(arg0, arg1, arg2);
            else
                Log.i(TAG, arg1);

        }

        @Override
        public void info(String arg0, Object arg1, Object arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.info(arg0, arg1, arg2);
            else
                Log.i(TAG, arg0);

        }

        @Override
        public void info(Marker arg0, String arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.info(arg0, arg1);
            else
                Log.i(TAG, arg1);
        }

        @Override
        public void info(String arg0, Throwable arg1) {

            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.info(arg0, arg1);
            else
                Log.i(TAG, arg0);
        }

        @Override
        public void info(String arg0, Object... arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.info(arg0, arg1);
            else
                Log.i(TAG, arg0);
        }

        @Override
        public void info(String arg0, Object arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.info(arg0, arg1);
            else
                Log.i(TAG, arg0);
        }

        @Override
        public void info(String arg0) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.info(arg0);
            else
                Log.i(TAG, arg0);
        }

        @Override
        public String getName() {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.getName();
            return null;
        }

        @Override
        public void error(Marker arg0, String arg1, Object arg2, Object arg3) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.error(arg0, arg1, arg2);
            else
                Log.e(TAG, arg1);
        }

        @Override
        public void error(Marker arg0, String arg1, Throwable arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.error(arg0, arg1, arg2);
            else
                Log.e(TAG, arg1);
        }

        @Override
        public void error(Marker arg0, String arg1, Object... arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.error(arg0, arg1, arg2);
            else
                Log.e(TAG, arg1);
        }

        @Override
        public void error(Marker arg0, String arg1, Object arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.error(arg0, arg1, arg2);
            else
                Log.e(TAG, arg1);
        }

        @Override
        public void error(String arg0, Object arg1, Object arg2) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.error(arg0, arg1, arg2);
            else
                Log.e(TAG, arg0);
        }

        @Override
        public void error(Marker arg0, String arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.error(arg0, arg1);
            else
                Log.e(TAG, arg1);
        }

        @Override
        public void error(String arg0, Throwable arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.error(arg0, arg1);
            else
                Log.e(TAG, arg0, arg1);
        }

        @Override
        public void error(String arg0, Object... arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.error(arg0, arg1);
            else
                Log.e(TAG, arg0);
        }

        @Override
        public void error(String arg0, Object arg1) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.error(arg0, arg1);
            else
                Log.e(TAG, arg0);
        }

        @Override
        public void error(String arg0) {
            if (DEFAULT_LOGGER_INTERIO != null)
                DEFAULT_LOGGER_INTERIO.error(arg0);
            else
                Log.e(TAG, arg0);
        }

        @Override
        public void debug(Marker arg0, String arg1, Object arg2, Object arg3) {
            if (DEFAULT_LOGGER_INTERIO != null && DEBUG)
                DEFAULT_LOGGER_INTERIO.debug(arg0, arg1, arg2);
            else
                Log.d(TAG, arg1);
        }

        @Override
        public void debug(Marker arg0, String arg1, Throwable arg2) {
            if (DEFAULT_LOGGER_INTERIO != null && DEBUG)
                DEFAULT_LOGGER_INTERIO.debug(arg0, arg1, arg2);
            else
                Log.d(TAG, arg1);
        }

        @Override
        public void debug(Marker arg0, String arg1, Object... arg2) {
            if (DEFAULT_LOGGER_INTERIO != null && DEBUG)
                DEFAULT_LOGGER_INTERIO.debug(arg0, arg1, arg2);
            else
                Log.d(TAG, arg1);
        }

        @Override
        public void debug(Marker arg0, String arg1, Object arg2) {
            if (DEFAULT_LOGGER_INTERIO != null && DEBUG)
                DEFAULT_LOGGER_INTERIO.debug(arg0, arg1, arg2);
            else
                Log.d(TAG, arg1);
        }

        @Override
        public void debug(String arg0, Object arg1, Object arg2) {
            if (DEFAULT_LOGGER_INTERIO != null && DEBUG)
                DEFAULT_LOGGER_INTERIO.debug(arg0, arg1, arg2);
            else
                Log.d(TAG, arg0);
        }

        @Override
        public void debug(Marker arg0, String arg1) {
            if (DEFAULT_LOGGER_INTERIO != null && DEBUG)
                DEFAULT_LOGGER_INTERIO.debug(arg0, arg1);
            else
                Log.d(TAG, arg1);
        }

        @Override
        public void debug(String arg0, Throwable arg1) {
            if (DEFAULT_LOGGER_INTERIO != null && DEBUG)
                DEFAULT_LOGGER_INTERIO.debug(arg0, arg1);
            else
                Log.d(TAG, arg0, arg1);
        }

        @Override
        public void debug(String arg0, Object... arg1) {
            if (DEFAULT_LOGGER_INTERIO != null && DEBUG)
                DEFAULT_LOGGER_INTERIO.debug(arg0, arg1);
            else
                Log.d(TAG, arg0);
        }

        @Override
        public void debug(String arg0, Object arg1) {
            if (DEFAULT_LOGGER_INTERIO != null && DEBUG)
                DEFAULT_LOGGER_INTERIO.debug(arg0, arg1);
            else
                Log.d(TAG, arg0);
        }

        @Override
        public void debug(String arg0) {
            if (DEFAULT_LOGGER_INTERIO != null && DEBUG)
                DEFAULT_LOGGER_INTERIO.debug(arg0);
            else
                Log.d(TAG, arg0);
        }
    };
}
