package com.polidea.getditlibs.network;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

public class RetrofitSpiceService extends RetrofitGsonSpiceService {

    final static String BASE_URL = "http://reddit.com";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(RedditService.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }
}
