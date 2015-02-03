package com.polidea.getditlibs.network;

import com.polidea.getditlibs.model.RResponse;
import retrofit.http.GET;

public interface RedditService {

    @GET("/r/gifs/.json?limit=25")
    RResponse getGifs();
}
