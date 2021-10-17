package com.deming.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.deming.blog.config.RequestServiceImpl;
import com.deming.blog.domain.Mlfruit;
import com.deming.blog.ml.ApiResp;
import com.deming.blog.ml.Fruit;
import com.deming.blog.ml.FruitClassification;
import com.deming.blog.resp.CommonResp;
import com.deming.blog.resp.FileUploadResp;
import com.deming.blog.service.MlfruitService;
import com.deming.blog.util.SnowFlake;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.Instant;
import java.util.List;
import java.util.Locale;

/**
 * File upload controller for mlfruit prediction
 */

@RestController
@RequestMapping("file")
public class FileController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${fruitclassification.apiurl}")
    private String FruitClassificationApiUrl;

    @Resource
    private RequestServiceImpl requestService;

    @Resource
    private MlfruitService mlfruitService;

    @Value("${file.upload.url}")
    private String uploadFilePath;

    @Resource
    private SnowFlake snowFlake;

    @Value("#{'${file.upload.suffix.allowed}'.split(',')}")
    private List<String> AllowedSuffix;


    /**
     * upload image and run prediction
     * @param file
     * @param request
     * @return CommonResp
     */
    @RequestMapping("/upload")
    public CommonResp httpUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        CommonResp resp = new CommonResp<>();
        try {
            String clientIP = requestService.getClientIp(request);
            log.info("Client IP:" + clientIP);

            if(file.isEmpty()){
                throw new Exception("Empty file");
            }

            JSONObject object=new JSONObject();
            String fileName = file.getOriginalFilename();

            log.info("filename=" + fileName);
            String extension = fileName.substring(fileName.lastIndexOf("."));
            log.info("suffix=" + extension);
            if(!AllowedSuffix.contains(extension.toLowerCase(Locale.ROOT))){
                throw new Exception("illegal image extension. supported extensions are:" + extension.toString());
            }
            String fileNameNew = String.valueOf(snowFlake.nextId()) + extension;
            File dest = new File(uploadFilePath +'/'+ fileNameNew);
            log.info("file dest=" + dest.getPath());
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            file.transferTo(dest);

            FileUploadResp<ApiResp<Fruit>> fileUploadResp = new FileUploadResp();
            fileUploadResp.setNameOri(fileName);
            fileUploadResp.setName(fileNameNew);
            fileUploadResp.setNameUrl("/image/upload/" + fileNameNew);
            FruitClassification fruitClassification = new FruitClassification();

            ApiResp<Fruit> apiResp = fruitClassification.predict(FruitClassificationApiUrl, fileNameNew);

            log.info(apiResp.toString());

            Fruit fruit = (Fruit) apiResp.getData();

            log.info("save fruit object");
            //Save result to database
            if (0==apiResp.getCode()){

                Mlfruit mlfruit = new Mlfruit();
                mlfruit.setId(snowFlake.nextId());
                mlfruit.setTimestamp(Instant.now().getEpochSecond());
                mlfruit.setImage(fileName);
                mlfruit.setImageUrl("/image/upload/" + fileNameNew);
                mlfruit.setProbability(Float.parseFloat(fruit.getProbability()));
                mlfruit.setResult(fruit.getResult());
                mlfruit.setIp(clientIP);

                //mlfruit.setContent(apiResp.toString());
                Gson gson = new Gson();
                mlfruit.setContent(gson.toJson(apiResp));

                log.info("mlfruit:");
                log.info(mlfruit.toString());

                mlfruitService.save(mlfruit);
            }

            fileUploadResp.setContent(apiResp);
            resp.setContent(fileUploadResp);
            resp.setMessage("upload success");
            log.info("upload success");
        }catch (Exception e){
            log.error(e.getMessage());
            resp.setSuccess(false);
            resp.setContent(null);
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

}