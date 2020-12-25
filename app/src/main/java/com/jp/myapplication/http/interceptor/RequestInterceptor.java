package com.jp.myapplication.http.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor{
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
//                .header("User-Agent", "YourAppName")
//                .header("Accept", "application/vnd.yourapi.v1.full+json")
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);
    }
}
