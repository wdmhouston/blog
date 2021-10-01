package com.deming.blog.ml;

import org.springframework.web.client.RestTemplate;

/**
 * Class for Fruit Classification Prediction
 * API access document: https://sc24613.readthedocs.io/en/main/
 * by Deming Wang
 * 2021/10/01
 */
public class fruitClassification {
    /**
     * Call predict api
     * image:  image name. Note: image file's folder should be shared with web api server for performance.
     */
    private static void predict(String image){
        final String uri = "http://localhost:8080/springrestexample/employees.xml";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

    }
}
