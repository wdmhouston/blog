package com.deming.blog.ml;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MlApiDataServiceFruit extends AbstractMlApiDataService{
    @Override
    Type getType() {
        return new TypeToken<ApiResp<Fruit>>(){}.getType();
    }

    public ApiResp<Fruit> getListFromGson(String str) {
        return super.getListFromGson(str);
    }
}
