package com.example.demo.web;

import com.example.demo.param.HtmlParam;
import com.example.demo.param.UserParam;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.*;
import java.io.*;
import java.util.UUID;

/**
 * ClassName: htmlController
 * Author: wangxiaokang
 * Date: 3/16/2023 9:52 AM
 * History:
 * <author>       <time>       <version>       <desc>
 * wangxiaokang   修改时间       1.0.0          备注信息
 */
@RestController
public class htmlController {


    public static class URLReader {
        public static void main(String[] args) throws Exception {
            URL url = new URL("https://www.baidu.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        }
    }

/*        @RequestMapping(value="/htmlData",method = RequestMethod.POST)
        public String addUser(@RequestBody HtmlParam param) throws IOException {
            URL url = new URL(param.getUrl());
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null){

                System.out.println(inputLine);
                in.close();
                return inputLine;
            }
            return null;
        }*/
    @RequestMapping(value = "/htmlData", method = RequestMethod.POST)
    public String addUser(@RequestBody HtmlParam param) throws IOException {
        JSONObject json = null;
        String str = null;
        try {
            // 创建URL对象
            URL url = new URL(param.getUrl());

            // 打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 读取数据
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            // 转换为JSON格式并输出
/*             json = new JSONObject(result.toString());
            System.out.println(json.toString());*/
            str = result.toString();
            // 解析为Document对象
            Document doc = Jsoup.parse(str);
            // 格式化为HTML字符串
            str = doc.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
