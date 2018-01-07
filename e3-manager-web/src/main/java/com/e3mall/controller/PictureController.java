package com.e3mall.controller;

import com.e3mall.utils.FastDFSClient;
import com.e3mall.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PictureController {
    @Value("${image.server.url}")
    private String url;

    @RequestMapping(value = "/pic/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String uploadPic(MultipartFile uploadFile){
        String filename = uploadFile.getOriginalFilename();
        String extname = filename.substring(filename.lastIndexOf(".")+1);
        Map map = new HashMap<>();
//        获取DFS客户端
        try {
            FastDFSClient client = new FastDFSClient("classpath:conf/client.conf");
            String fileUrl = client.uploadFile(uploadFile.getBytes(), extname);
            fileUrl = url+fileUrl;
//           返回图片地址
            map.put("error",0);
            map.put("url",fileUrl);
            return JsonUtils.objectToJson(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("message","图片上传失败");
            return JsonUtils.objectToJson(map);
        }
    }
}
