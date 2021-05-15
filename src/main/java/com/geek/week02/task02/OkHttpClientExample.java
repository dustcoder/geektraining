package com.geek.week02.task02;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpClientExample {

    private static final String URL = "http://localhost:8081";

    private final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        OkHttpClientExample okHttpClientExample = new OkHttpClientExample();
        okHttpClientExample.sendGetRequest();
    }

    private void sendGetRequest() {
        Request request = new Request.Builder().url(URL).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("request fail" + response);
            }
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
