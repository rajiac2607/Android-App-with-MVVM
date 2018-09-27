package com.nyt.articles.data.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Raji on 26/09/2018.
 */
public class RequestInterceptor implements Interceptor {

    /**
     * interceptor to add header to each request
     *
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().addHeader("api-key", APIConfig.NYT_API_KEY).build();
        return chain.proceed(request);
    }
}

