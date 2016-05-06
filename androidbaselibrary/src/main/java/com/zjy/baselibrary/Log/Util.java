package com.zjy.baselibrary.log;

import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志的公用方法
 * Created by jiyoung.tsang on 16/4/21.
 */
public class Util {

    /**
     * 日志级别
     */
    private static final int LOG_LEVEL = Level.VERBOSE;



    /**
     * 打印Log的公共方法
     *
     * @param logLevel
     * @param tag
     * @param msg
     */
    public static void printLog(int logLevel, String tag, String msg) {
        StackTraceElement st = Thread.currentThread().getStackTrace()[4];// 获取当前线程的堆栈信息
        if (TextUtils.isEmpty(tag)) {
            tag = st.getFileName();
        }

        String mess = "";

        if (LOG_LEVEL <= logLevel) {// 等级控制
            if (LogLib.debugFlag) {
                mess = getLogCatString(msg, st);// 在logcat的输出格式
                printDefault(logLevel, tag, mess);
            }
        }
    }

    public static void printFile(String tag,String msg){
        StackTraceElement st = Thread.currentThread().getStackTrace()[4];// 获取当前线程的堆栈信息
        if (TextUtils.isEmpty(tag)) {
            tag = st.getFileName();
        }

        String mess = getLogFileString(msg,st);

        printFile(tag,mess);
    }

    /**
     * 在logcat的输出格式
     * @param msg
     * @param st
     * @return
     */
    private static String getLogCatString(String msg,
                                         StackTraceElement st) {
        StringBuilder sb = new StringBuilder("");
        sb.append("{").append(getTraceInfo(st)).append(msg).append(" }");
        return sb.toString();
    }

    /**
     * 在输出的日志内容里添加一个前缀，方便查看代码位置,和点击跳转
     *
     * @return [ (类:行数) # 方法 ]
     */
    private static String getTraceInfo(StackTraceElement st) {
        StringBuilder sb = new StringBuilder("");
        sb.append(" [ (").append(st.getFileName()).append(":")
                .append(st.getLineNumber()).append(") # ")
                .append(st.getMethodName()).append(" ]");
        return sb.toString();

    }

    /**
     * 在文件中的输出格式
     * @param msg
     * @param st
     * @return
     */
    public static String getLogFileString(String msg, StackTraceElement st) {

        StringBuilder sb = new StringBuilder("");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 日期时间格式
        String date = sdf.format(new Date());
        // 在文件中的输出格式

        sb.append("\n{ ").append(date).append("  ").append(getTraceInfo(st)).append(msg).append(" }\r\n");

        return sb.toString();
    }

    private static void printDefault(int type, String tag, String msg) {
        switch (type) {
            case Level.VERBOSE:
                Log.v(tag, msg);
                break;
            case Level.DEBUG:
                Log.d(tag, msg);
                break;
            case Level.INFO:
                Log.i(tag, msg);
                break;
            case Level.WARN:
                Log.w(tag, msg);
                break;
            case Level.ERROR:
                Log.e(tag, msg);
                break;
        }
    }
}
