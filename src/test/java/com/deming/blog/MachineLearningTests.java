package com.deming.blog;

import com.alibaba.fastjson.JSONObject;
import com.deming.blog.domain.Mlfruit;
import com.deming.blog.ml.ApiResp;
import com.deming.blog.ml.Fruit;
import com.deming.blog.service.MlfruitService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.List;

/**
 * MachieLearningTests
 * Test this with AI/ML server
 * Code is disabled for local test
 */

@SpringBootTest
public class MachineLearningTests {

    @Test
    void fruitClassification() {
//        System.out.println("fruitClassification test");
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        String  result = restTemplate.getForObject("http://localhost:8880/category/all"
//                , String.class);
//        /**
//         * returned format:
//         * {"success":true,"content":[{"parent":200,"name":"test1","id":98520355529953280,"sort":1}...]}
//         */
//
//        JSONObject jsonObject = JSONObject.parseObject(result);
//
//        if(jsonObject.containsKey("xx")){
//            System.out.println("has xx");
//        }else{
//            System.out.println("has no xx");
//        }
//        List<DataBean> list = (List<DataBean>) jsonObject.get("content");
//        System.out.println(jsonObject.get("success"));
//        System.out.println(jsonObject.toString());
    }

//    @Test
//    void GsonTest(){
//        String image = "apple_1.jpg";
//        String result = "{'code':0,'message':'ok','data':{'image':'/app/project/upload/apple_1.jpg','result':'apple'}}";
//        System.out.println("Api FruitClassification.predict called: image=" + image);
//        System.out.println("Api returned:");
//        System.out.println(result);
//
//        Gson gson = new Gson();
//        ApiResp<Fruit> apiResp = gson.fromJson(result, ApiResp.class);
//
//        String fileName = "apple_1.jpg";
//        String fileNameNew = "apple_1_new.jpg";
//
//        if (0==apiResp.getCode()){
//            System.out.println("save to database");
//            Mlfruit mlfruit = new Mlfruit();
//            mlfruit.setImage(fileName);
//            System.out.println("1");
//            mlfruit.setContent(apiResp.toString());
//            System.out.println(apiResp.toString());
//            System.out.println("2");
//            System.out.println(apiResp.getData());
//            Fruit fruit = (Fruit)apiResp.getData();
//            System.out.println("2.5");
//            mlfruit.setResult(fruit.getResult());
//            System.out.println("3");
//            mlfruit.setIp("ip");
//            mlfruit.setProbability(0.98f);
//            mlfruit.setImageUrl("/image/upload/" + fileNameNew);
//            MlfruitService mlfruitService = new MlfruitService();
//            mlfruitService.save(mlfruit);
//            System.out.println(mlfruit.toString());
//            System.out.println("save to database done");
//        }
//    }
}
