package com.deming.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.deming.blog.req.EbookQueryReq;
import com.deming.blog.req.EbookSaveReq;
import com.deming.blog.resp.CommonResp;
import com.deming.blog.resp.EbookQueryResp;
import com.deming.blog.resp.FileUploadResp;
import com.deming.blog.resp.PageResp;
import com.deming.blog.service.EbookService;
import com.deming.blog.util.SnowFlake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("file")
public class FileController {
    @Value("${file.upload.url}")
    private String uploadFilePath;

    @Resource
    private SnowFlake snowFlake;

    @Value("#{'${file.upload.suffix.allowed}'.split(',')}")
    private List<String> AllowedSuffix;


    @RequestMapping("/upload")
    public CommonResp httpUpload(@RequestParam("file") MultipartFile file){

        CommonResp resp = new CommonResp<>();
        try {
            if(file.isEmpty()){
                throw new Exception("Empty file");
            }

            JSONObject object=new JSONObject();
            String fileName = file.getOriginalFilename();
            System.out.println("filename=" + fileName);
            String extension = fileName.substring(fileName.lastIndexOf("."));
            System.out.println("suffix=" + extension);
            if(!AllowedSuffix.contains(extension.toLowerCase(Locale.ROOT))){
                throw new Exception("illegal image extension. supported extensions are:" + extension.toString());
            }
            String fileNameNew = String.valueOf(snowFlake.nextId()) + extension;
            File dest = new File(uploadFilePath +'/'+ fileNameNew);
            System.out.println("file dest=" + dest.getPath());
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            file.transferTo(dest);

            FileUploadResp fileUploadResp = new FileUploadResp();
            fileUploadResp.setNameOri(fileName);
            fileUploadResp.setName(fileNameNew);
            fileUploadResp.setNameUrl("/image/" + fileNameNew);
            resp.setContent(fileUploadResp);
            resp.setMessage("upload success");
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setContent(null);
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

}
