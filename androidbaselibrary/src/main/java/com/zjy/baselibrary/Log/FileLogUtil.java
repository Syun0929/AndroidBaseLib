package com.zjy.baselibrary.log;

import android.os.Environment;
import android.text.TextUtils;

import com.zjy.baselibrary.threadhelper.ThreadPool;
import com.zjy.baselibrary.utils.DateUtil;
import com.zjy.baselibrary.utils.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 日志文件的一些操作
 * Created by jiyoung.tsang on 16/5/6.
 */
public class FileLogUtil {

    private static final String FILE_TYPE = ".txt";// 文件类型
    private static final String LAST_DATE_FILE = "predate.txt";

    /**
     * 写入Log到日志文件中
     *
     * @param log 日志信息
     */
    public static void saveLogInSdcard(String log) {
        // 有无外部存储空间
        if (!Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState()))
            return;
        try {
            byte[] buffer = log.getBytes("UTF-8");

            saveLog(buffer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存log
     *
     * @param log log detail
     */
    private static void saveLog(byte[] log) {
        FileUtil.createFolderIfNotExist(LogLib.getProjectLogDirPath());

        String date = getDateByGap(7);
        String logFileName = "log_" + date + FILE_TYPE;// 每周只生成一个日志文件
        ThreadPool.GENERAL_THREAD_POOL.execute(new WriteTask(log, LogLib.getProjectLogDirPath() + logFileName));
    }

    /**
     * 每隔gap天新建一个文件
     *
     * @param gap gap
     * @return String
     */
    private static String getDateByGap(int gap) {
        String preDate = readFromFile(LogLib.getProjectLogDirPath() + LAST_DATE_FILE);
        try {
            if (TextUtils.isEmpty(preDate)) {
                preDate = DateUtil.getTodayDate();
                writeInFile(preDate.getBytes("UTF-8"), LogLib.getProjectLogDirPath()
                        + LAST_DATE_FILE);
            } else {
                String date = DateUtil.getTodayDate();
                if (DateUtil.getTwoDayBetween(preDate, date) >= gap) {
                    preDate = date;
                    writeInFile(preDate.getBytes("UTF-8"), LogLib.getProjectLogDirPath()
                            + LAST_DATE_FILE);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return preDate;
    }

    /**
     * 写入文件中，覆盖写入
     *
     * @param buffer buffer
     * @param absFilePath absFilePath
     */
    private static void writeInFile(byte[] buffer, String absFilePath) {
        if (!TextUtils.isEmpty(absFilePath)) {
            FileOutputStream fos = null;
            try {

                File file = new File(absFilePath);
                if (!file.exists())
                    file.createNewFile();
                fos = new FileOutputStream(file, false);// 覆盖写入
                fos.write(buffer);
                fos.flush();

            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * 按行读取文件内容
     *
     * @param absFilePath absFilePath
     * @return String String
     */
    private static String readFromFile(String absFilePath) {
        if (!TextUtils.isEmpty(absFilePath)) {
            BufferedReader bufferedReader = null;
            try {
                File file = new File(absFilePath);
                if (!file.exists()) {
                    file.createNewFile();
                    return null;
                }
                bufferedReader = new BufferedReader(new FileReader(file));
                StringBuilder stringBuffer = new StringBuilder("");
                String data;
                while ((data = bufferedReader.readLine()) != null) {
                    stringBuffer.append(data.trim());
                }

                bufferedReader.close();
                return stringBuffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {

                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
