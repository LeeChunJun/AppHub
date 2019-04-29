package com.licj.apphub.commons.net;


import com.licj.apphub.index.IndexService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by licj on 2018/3/8.
 */

public class Api {

    public static final String API_BASE = "http://127.0.0.1";
    public static final String API_BASE_URL = API_BASE + "/app/mvp/";


    private static Retrofit retrofit;
    private static IndexService indexService;

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (Api.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
                }
            }

        }
        return retrofit;
    }

    public static IndexService getIndexService() {
        if (indexService == null) {
            synchronized (Api.class) {
                if (indexService == null) {
                    indexService = getRetrofit().create(IndexService.class);
                }
            }
        }
        return indexService;
    }


}
