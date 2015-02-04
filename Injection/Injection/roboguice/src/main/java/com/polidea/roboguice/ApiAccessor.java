package com.polidea.roboguice;

import com.google.inject.Singleton;
import roboguice.inject.InjectResource;

@Singleton
public class ApiAccessor {

    @InjectResource(R.string.host_url)
    String hostUrl;

    public ApiAccessor(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public boolean sendMessage(String message) {
        boolean result = true;
        try {
            //sending message
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            result = false;
        }
        return result;
    }
}
