package com.zjy.baselibrary.utils;

import java.io.File;

/**
 * 文件辅助类
 * Created by jiyoung.tsang on 16/5/6.
 */
public class FileUtil {
    public static void createFolderIfNotExist(String absFolderPath) {
        File file = new File(absFolderPath);
        if (!file.exists())
            file.mkdirs();
    }
}
