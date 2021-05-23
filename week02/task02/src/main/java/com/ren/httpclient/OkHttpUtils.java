package com.ren.httpclient;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpUtils {

    private static final String URL = "http://localhost:8803";

    private final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws Exception{
        OkHttpUtils okHttpUtils = new OkHttpUtils();
        System.out.println(okHttpUtils.sendGetRequest());
    }

    private String sendGetRequest() throws IOException{
        Request request = new Request.Builder().url(URL).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


}
