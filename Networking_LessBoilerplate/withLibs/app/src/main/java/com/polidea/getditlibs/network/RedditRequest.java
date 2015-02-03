package com.polidea.getditlibs.network;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.polidea.getditlibs.model.RResponse;

public class RedditRequest extends RetrofitSpiceRequest<RResponse, RedditService> {

    public RedditRequest() {
        super(RResponse.class, RedditService.class);
    }

    @Override
    public RResponse loadDataFromNetwork() throws Exception {
        return getService().getGifs();
    }
}
