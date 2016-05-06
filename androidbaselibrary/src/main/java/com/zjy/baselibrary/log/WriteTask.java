package com.zjy.baselibrary.log;

import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by jiyoung.tsang on 16/5/6.
 */
public class WriteTask implements Runnable {

    private byte[] buffer;
    private String absLogPath;
    private static FileOutputStream fos = null;

    public WriteTask(byte[] buffer, String absLogPath) {
        super();
        this.buffer = buffer;
        this.absLogPath = absLogPath;

    }

    public void run() {
        writeInFile(buffer, absLogPath);
    }

    /**
     * 写入文件中，文件已存在就追加到文件尾，不存在则新建文件进行写入
     *
     * @param buffer
     * @param absLogPath
     * @return 写入成功返回true，否则返回false
     */
    private void writeInFile(byte[] buffer, String absLogPath) {
        if (!TextUtils.isEmpty(absLogPath)) {

            try {

                File file = new File(absLogPath);
                if (!file.exists())
                    file.createNewFile();
                if (file.length() > 0) {
                    fos = new FileOutputStream(file, true);// 追加到文件尾
                } else {
                    fos = new FileOutputStream(file, false);
                }
                fos.write(buffer);
                fos.flush();

            } catch (IOException e) {
                    e.getStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();

                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                }
            }
        }

    }
}
