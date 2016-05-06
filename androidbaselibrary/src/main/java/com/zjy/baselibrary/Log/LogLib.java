package com.zjy.baselibrary.log;


import android.text.TextUtils;

/**
 * 日志框架。
 * 1.只输出级别大于LOG_LEVEL的日志 。
 * 2.点击跳转
 * 3.打印代码行号
 * 4.支持打印日志文件到sd卡,日志文件目录由使用者自行初始化
 * Created by jiyoung.tsang on 16/4/21.
 */
public class LogLib {
    /** 是否开启调试 */
    public static boolean debugFlag = true;

    /**日志文件路径*/
    private static String projectLogDirPath;

    /**
     * 要保存日志到本地,必须先设置日志目录路径
     * @param logDirPath
     */
    public static void initLogDir(String logDirPath){
        LogLib.projectLogDirPath = logDirPath;
    }

    public static String getProjectLogDirPath() {
        return projectLogDirPath;
    }

    /**
     * 打印调试信息 level:v . tag默认为所在类名
     * @param msg
     */
    public static void v(String msg){
        Util.printLog(Level.VERBOSE, "", msg);
    }

    /**
     * 打印调试信息 level:d . tag默认为所在类名
     * @param msg
     */
    public static void d(String msg){
        Util.printLog(Level.DEBUG, "", msg);
    }

    /**
     * 打印调试信息 level:i . tag默认为所在类名
     * @param msg
     */
    public static void i(String msg){
        Util.printLog(Level.INFO, "", msg);
    }

    /**
     * 打印调试信息 level:w . tag默认为所在类名
     * @param msg
     */
    public static void w(String msg){
        Util.printLog(Level.WARN, "", msg);
    }

    /**
     * 打印调试信息 level:e . tag默认为所在类名
     * @param msg
     */
    public static void e(String msg){
        Util.printLog(Level.ERROR, "", msg);
    }

    /**
     * 打印调试信息 level:v .
     * @param msg
     */
    public static void v(String tag, String msg){
        Util.printLog(Level.VERBOSE, tag, msg);
    }

    /**
     * 打印调试信息 level:d .
     * @param msg
     */
    public static void d(String tag, String msg){
        Util.printLog(Level.DEBUG, tag, msg);
    }

    /**
     * 打印调试信息 level:i .
     * @param msg
     */
    public static void i(String tag, String msg){
        Util.printLog(Level.INFO, tag, msg);
    }

    /**
     * 打印调试信息 level:w .
     * @param msg
     */
    public static void w(String tag, String msg){
        Util.printLog(Level.WARN, tag, msg);
    }

    /**
     * 打印调试信息 level:e .
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg){
        Util.printLog(Level.ERROR, tag, msg);
    }

    /**
     * 保存日志到文件,使用前先在application全局类初始化日志文件目录,用方法 initLogDir(String logDirPath)
     * @param msg
     */
    public static void file(String msg){
        if(TextUtils.isEmpty(getProjectLogDirPath())){
            return;
        }
        Util.printFile("",msg);
    }

    /**
     * 保存日志到文件,使用前先在application全局类初始化日志文件目录,用方法 initLogDir(String logDirPath)
     * @param tag
     * @param msg
     */
    public static void file(String tag,String msg){
        if(TextUtils.isEmpty(getProjectLogDirPath())){
            return;
        }
        Util.printFile(tag,msg);
    }
}
