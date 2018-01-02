package com.zero.logs;

import android.util.Log;

import java.util.Locale;

/**
 * 日志打印的工具类,该类提供5种级别的日志,对日志的打印方式进行
 * Created by LeeYewKuan on 2018/1/2.
 */

public final class Logs {
    /**
     * 是否开启调试模式.
     */
    private static boolean debug = true;
    /**
     * 是否展示调用线程的名字和ID.
     */
    private static boolean showThread = false;
    /**
     * 是否展示调用方法的名字
     */
    private static boolean showMethod = true;
    /**
     * 展示类的全路径名还是文件名
     */
    private static boolean showClassName = false;
    /**
     * 栈信息类
     */
    private static StackTraceElement stacks;
    /**
     * 基准时间
     */
    private static long baseTime = 0;
    /**
     * 两次日志间隔时间
     */
    private static long stepTime = 0;

    private Logs() {
    }

    /**
     * 初始化日志工具类.
     *
     * @param isDebug         是否开启调试模式,true 开启,false 不开启,默认 true.
     * @param isShowThread    是否显示调用线程, true 展示,false 不展示,默认 false.
     * @param isShowMethod    是否显示调用方法, true 显示,false 不显示,默认 true.
     * @param isShowClassName 是否显示方法被调用处的类名,true 显示类名 com.zero.**, false 显示文件名 ** false
     */
    public static void initLogs(boolean isDebug, boolean isShowThread, boolean isShowMethod, boolean isShowClassName) {
        debug = isDebug;
        showThread = isShowThread;
        showMethod = isShowMethod;
        showClassName = isShowClassName;
    }

    public static void v(String logMsg) {
        if (debug) {
            stacks = (new Throwable()).getStackTrace()[1];
            Log.v(getLogTag(), getLogMessage(logMsg));
        }
    }

    public static void d(String logMsg) {
        if (debug) {
            stacks = (new Throwable()).getStackTrace()[1];
            Log.d(getLogTag(), getLogMessage(logMsg));
        }

    }

    public static void i(String logMsg) {
        if (debug) {
            stacks = (new Throwable()).getStackTrace()[1];
            Log.i(getLogTag(), getLogMessage(logMsg));
        }

    }

    public static void w(String logMsg) {
        if (debug) {
            stacks = (new Throwable()).getStackTrace()[1];
            Log.w(getLogTag(), getLogMessage(logMsg));
        }
    }

    public static void e(String logMsg) {
        if (debug) {
            stacks = (new Throwable()).getStackTrace()[1];
            Log.e(getLogTag(), getLogMessage(logMsg));
        }
    }

    /**
     * 获取日志的TAG
     *
     * @return 类全名, 文件名, 默认 文件名
     */
    private static String getLogTag() {
        String tag;
        if (showClassName) {
            tag = stacks.getClassName();
        } else {
            tag = stacks.getFileName();
            tag = tag.substring(0, tag.lastIndexOf('.'));
        }
        return tag;
    }

    /**
     * 获取拼接后的日志内容.
     *
     * @param logMsg 输入的信息
     * @return 子集: M[%s],T[%s-%d],L[%d],Msg[%s]
     */
    private static String getLogMessage(String logMsg) {
        String threadName;
        String methodName;
        long id;

        StringBuilder builder = new StringBuilder();
        if (showMethod) {
            methodName = stacks.getMethodName();
            builder.append(String.format(Locale.CHINA, "M[%s]", methodName));
        }
        if (showThread) {
            Thread thread = Thread.currentThread();
            id = thread.getId();
            threadName = thread.getName();
            builder.append(String.format(Locale.CHINA, ">T[%s,%d]", threadName, id));
        }
        if (baseTime > 0){
            builder.append(String.format(Locale.CHINA,">S[%d]",System.currentTimeMillis() - baseTime));
        }
        int lineNumber = stacks.getLineNumber();
        builder.append(String.format(Locale.CHINA, ">L[%d]>Msg[%s]", lineNumber, logMsg));
        return builder.toString();
    }

    /**
     * 还原默认值
     */
    public static void restoreDefault() {
        debug = true;
        showThread = false;
        showMethod = true;
        showClassName = false;
    }

    public static void startTransaction(){
        baseTime = System.currentTimeMillis();
    }

    public static void endTransaction(){
        baseTime = 0;
    }
}
