package com.deming.blog.ml;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Class for Fruit Classification Prediction
 * API access document: https://sc24613.readthedocs.io/en/main/
 */
@Service
public class FruitClassification {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Call predict api
     * image:  image name. Note: image file's folder should be shared with web api server for performance.
     * api url  http://localhost:5000/api/predict/{image}
     * returned format:
     *    {"'code':0,
     *    'message':'ok',
     *    'data':{'image':'/app/project/upload/apple_1.jpg',
     *            'result':'apple',
     *            'probability':"0.97"
     *            }
     *     }"
     */
    public ApiResp<Fruit> predict(String apiurl, String image){
        RestTemplate restTemplate = new RestTemplate();
        try{
            URI uri = new URI(apiurl + image);
            log.info("api uri:", uri.toString());
            String  result = restTemplate.getForObject(uri, String.class);

            log.info("Api FruitClassification.predict called: image=" + image);
            log.info("Api returned:");
            log.info(result);

            MlApiDataServiceFruit mlApiDataServiceFruit = new MlApiDataServiceFruit();
            ApiResp<Fruit> apiResp = mlApiDataServiceFruit.getListFromGson(result);
            return apiResp;
        }catch (Exception e){
            log.error(e.getMessage());
            ApiResp<Fruit> apiResp = new ApiResp<>();
            apiResp.setCode(1);
            apiResp.setMessage(e.getMessage());
            return apiResp;
        }
    }
}
