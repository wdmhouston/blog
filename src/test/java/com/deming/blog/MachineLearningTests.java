package com.deming.blog;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.List;

@SpringBootTest
public class MachineLearningTests {

    @Test
    void fruitClassification() {
        System.out.println("fruitClassification test");

        RestTemplate restTemplate = new RestTemplate();

        String  result = restTemplate.getForObject("http://localhost:8880/category/all"
                , String.class);
        /**
         * returned format:
         * {"success":true,"content":[{"parent":200,"name":"test1","id":98520355529953280,"sort":1}...]}
         */

        JSONObject jsonObject = JSONObject.parseObject(result);

        if(jsonObject.containsKey("xx")){
            System.out.println("has xx");
        }else{
            System.out.println("has no xx");
        }
        List<DataBean> list = (List<DataBean>) jsonObject.get("content");
        System.out.println(jsonObject.get("success"));
        System.out.println(jsonObject.toString());
    }

}
