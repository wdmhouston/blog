package com.deming.blog.ml;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public abstract class AbstractMlApiDataService<T> {
    abstract Type getType();
    public ApiResp<T> getListFromGson(String str) {
        ApiResp<T> result = null;
        try {
            result = new Gson().fromJson(str, getType());
        } catch (Exception e) {}
        return result;
    }
}
