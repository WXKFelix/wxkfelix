package com.example.demo.web;

import com.example.demo.param.UserParam;
import com.example.demo.service.DemoService;
import com.example.demo.vo.DemoVo;
import com.example.demo.vo.UploadVo;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.annotation.Resource;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ClassName: HelloController
 * Author: wangxiaokang
 * Date: 2/2/2023 5:43 PM
 * History:
 * <author>       <time>       <version>       <desc>
 * wangxiaokang   修改时间       1.0.0          备注信息
 */
@RestController
public class HelloController {
    @Resource
    private DemoService demoService;

    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String sayHello(){
        return "Hello, World!";
    }

    @RequestMapping(value="/test",method = RequestMethod.GET)
    public List<DemoVo> test(){
        return demoService.selectInfo();
    }

    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    public Integer addUser(@RequestBody UserParam userParam){
        userParam.setId(UUID.randomUUID().toString().replaceAll("-",""));
        return demoService.addUser(userParam);
    }

    @RequestMapping(value="/upload",method = RequestMethod.POST)
    public List<UploadVo> upload(List<MultipartFile> files) throws IOException {
        List<UploadVo> list = new ArrayList<>();
        files.forEach(e->{
            UploadVo uploadVo = new UploadVo();
            try {
            File toFile=null;
            if (e.equals("") || e.getSize() <= 0) {
                e = null;
            } else {
                InputStream ins = null;
                ins = e.getInputStream();
                toFile = new File(e.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
            }

            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);

                Document doc =  f.createDocument(toFile.toURI().toString());
                Element element1 = doc.getDocumentElement();
                String elementStr1 = convertElemToSVG(element1);

                // 1.字符串转为字节数组
                byte[] byteArray = elementStr1.getBytes(StandardCharsets.UTF_8);
                // 2.构造字节数组输入流
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
                // 3.构造输入流读取器
                InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream); //, StandardCharsets.UTF_8);
                // 4.构造缓冲型读取器
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                FileWriter removeLine=new FileWriter(toFile);
                BufferedWriter change=new BufferedWriter(removeLine);
                PrintWriter replace2=new PrintWriter(change);
                // 5.循环读取每行字符串，并做必要处理
                int i = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    if(i == 3){
                        String strings = StringUtils.substringBetween(line, "\"", "\"");
                        uploadVo.setName(strings);
                    }
                    if(line.contains("title")){
                        replace2.println(line);
                        replace2.flush();
                        continue;
                    }
                    if(line.contains("path")){
                        line.replace("fill=\"#000000\"","fill=\"currentColor\"");
                    }
                    // 去掉每行两端的空格，并重新拼接为一行
                    stringBuilder.append(line.trim());
                    i++;
                }

                byteArrayInputStream.close();
                inputStreamReader.close();
                bufferedReader.close();
                String s = stringBuilder.toString();
                String replace = s.replace("fill=\"#000000\"", "fill=\"currentColor\"");
                System.out.println(replace);

                uploadVo.setSvg(replace);
                uploadVo.setType("新增修改图标");
                list.add(uploadVo);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        return list;
    }

    public String convertElemToSVG(Element element) {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        StringWriter buffer = new StringWriter();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        try {
            DOMSource domSource = new DOMSource(element);
            transformer.transform(domSource, new StreamResult(buffer));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        String elementStr = buffer.toString();
        return elementStr;
    }

    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            //把字节流读到缓冲区buffer，从缓存区的坐标0开始放，放到8192
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            //关闭输入输出流
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String str = "path fill=\"#000000\" d=";
        String replace = str.replace("fill=\"#000000\"", "fill=\"currentColor\"");
        str.replace("fi","fill=\"currentColor\"");
        System.out.println(replace);
        StringUtils.replace("fill","\"","fill=\"currentColor\"");

    }

}
