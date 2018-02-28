package org.lmy.open.utillibrary;

/**********************************************************************
 *
 *
 * @类名 LogHelper
 * @包名 org.lmy.open.utillibrary
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public class LogHelper {
    /**
     * 使用的logger
     */
    private static org.slf4j.Logger sLogger = LoggerUtil.DEFAULT_LOGGER;
    /**
     * 标签
     */
    private static final String TAG = "WanAndroid-log";
    /**
     * 是否为debug版本
     */
    private static boolean isDebug = true;
    /**
     * 分隔符
     */
    private static final String SPLIT = isDebug ? "liumy====" : "====";

    /**
     * debug log
     *
     * @param log log内容
     */
    public static void d(String log) {
        StackTraceElement[] e = new Throwable().getStackTrace();
        if (e.length >= 1) {
            String result = format(e[1], log);
            sLogger.debug(result);
        }
    }

    /**
     * information log
     *
     * @param log log内容
     */
    public static void i(String log) {
        StackTraceElement[] e = new Throwable().getStackTrace();
        if (e.length >= 1) {
            String result = format(e[1], log);
            sLogger.info(result);
        }
    }

    /**
     * error log
     *
     * @param log log内容
     */
    public static void e(String log) {
        StackTraceElement[] e = new Throwable().getStackTrace();
        if (e.length >= 1) {
            String result = format(e[1], log);
            sLogger.error(result);
        }
    }

    /**
     * 格式化Log
     *
     * @param e   StackTraceElement
     * @param log log内容
     * @return 格式化结果
     */
    private static String format(StackTraceElement e, String log) {
        if (log == null) {
            log = "";
        }
        if (e == null) {
            return log;
        }
        StringBuffer stringBuffer = new StringBuffer(TAG);
        stringBuffer.append(SPLIT);
        stringBuffer.append("[");
        stringBuffer.append(e.getFileName());
        stringBuffer.append("#");
        stringBuffer.append(e.getLineNumber());
        stringBuffer.append("#");
        stringBuffer.append(e.getMethodName());
        stringBuffer.append("]");
        stringBuffer.append(SPLIT);
        stringBuffer.append(log);
        return stringBuffer.toString();
    }

    /**
     * 全路径显示调用栈
     *
     * @param log log内容
     */
    public static void dFullPath(String log) {
        String result = "";
        StackTraceElement[] e = new Throwable().getStackTrace();
        if (e.length >= 1) {
            StringBuffer stringBuffer = new StringBuffer(TAG);
            for (StackTraceElement stackTraceElement : e) {
                stringBuffer.append(SPLIT);
                stringBuffer.append("[");
                stringBuffer.append(stackTraceElement.getFileName());
                stringBuffer.append("#");
                stringBuffer.append(stackTraceElement.getLineNumber());
                stringBuffer.append("#");
                stringBuffer.append(stackTraceElement.getMethodName());
                stringBuffer.append("]");
            }
            stringBuffer.append(SPLIT);
            result = stringBuffer.toString() + log;
            sLogger.error(result);
        }
    }
}
