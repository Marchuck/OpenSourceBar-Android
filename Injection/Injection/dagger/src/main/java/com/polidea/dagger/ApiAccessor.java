package com.polidea.dagger;


public class ApiAccessor {

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
