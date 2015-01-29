package com.polidea.injection;


import android.content.Context;

public class ApiAccessor {

    private String hostUrl;

    public ApiAccessor(Context context) {
        hostUrl = context.getString(R.string.host_url);
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
