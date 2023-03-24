package com.example.demo.web;

import com.example.demo.param.HtmlParam;
import com.example.demo.vo.Result;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.io.*;

/**
 * ClassName: htmlController
 * Author: wangxiaokang
 * Date: 3/16/2023 9:52 AM
 * History:
 * <author>       <time>       <version>       <desc>
 * wangxiaokang   修改时间       1.0.0          备注信息
 */
@RestController
@RequestMapping("/test")
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


    @RequestMapping(value = "/getHtmlDatas", method = RequestMethod.GET)
    public Result getHtmlData() throws IOException {
        Result result = new Result();
        result.setData("===================");
        result.setCode(200);
        result.setMessage("测试OK");
        return result;
    }

    public static void main(String[] args) {
        String str = "<!DOCTYPE html><html lang=\\\"en\\\">";
        String s = str.replaceAll("\\\\", "");
        System.out.println(s);
    }

    @PostMapping(value = "/getHtmlData",produces = "application/json;charset=UTF-8;MediaType.APPLICATION_JSON_VALUE")
    @ResponseBody
    public Result getHtmlData(@RequestBody HtmlParam param) throws IOException, InterruptedException {
        String exe = "python";
        String command = "src/main/resources/CatchCodeByUrl.py";
        String url = param.getUrl();
        String[] cmdArr = new String[] {exe,command,url};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readLine();
        process.waitFor();
        System.out.println(str);
        // 读取文件
        BufferedReader reader = new BufferedReader(new FileReader("python.html"));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // 去除多余的空格和换行符
                //line = line.trim().replaceAll("\\s+", " ");
                stringBuilder.append(line);
            }
            // 输出成一行字符串
            System.out.println(stringBuilder.toString());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Result result = new Result();
        String string = stringBuilder.toString();
        String replaceAll = string.replaceAll("\\\\", "");
        String unescapeJava = StringEscapeUtils.unescapeJava(string);
        result.setData(unescapeJava);
        result.setCode(200);
        result.setMessage("成功");
        File myObj = new File("python.html");
        if (myObj.delete()) {
            System.out.println("已经删除相关的" + myObj.getName()+"文件!");
        } else {
            System.out.println("Failed to delete the file.");
        }
        System.out.println(result);
        return result;
    }


}
