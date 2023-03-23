package com.example.demo.web;

import org.python.util.PythonInterpreter;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * ClassName: PyTestController
 * Author: wangxiaokang
 * Date: 3/22/2023 4:58 PM
 * History:
 * <author>       <time>       <version>       <desc>
 * wangxiaokang   修改时间       1.0.0          备注信息
 */
public class PyTestController {
    public static void main(String[] args) throws IOException, InterruptedException {
        String exe = "python";
        String command = "src/main/resources/CatchCodeByUrl.py";
        String url = "https://www.baidu.com";
        String[] cmdArr = new String[] {exe,command,url};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readLine();
        process.waitFor();
        System.out.println(str);
    }
}
