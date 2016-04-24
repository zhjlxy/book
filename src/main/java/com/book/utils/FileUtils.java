package com.book.utils;

import java.io.*;

/**
 * Created by lixuy on 2016/4/23.
 */
public class FileUtils {

    public static  void upload(InputStream in, File resultFile) throws IOException {
        OutputStream outputStream = new FileOutputStream(resultFile);
        byte[] bytes = new byte[1024];

        int len = 0;
        while((len = in.read(bytes))>0){
            outputStream.write(bytes,0,len);
        }
    }
}
