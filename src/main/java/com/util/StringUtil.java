package com.util;

import java.io.File;

/**
 * Created by tangly on 2017/11/15.
 */
public class StringUtil {

    /**
     * 获取文件后缀名
     * @param file
     * @return
     */
    public static String getFileExtension(File file ){
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
       return suffix;
    }
}
