package com.polidea.opensourcebar.rxsample.network;

public class Log {

    public static void log(String msg, Object... params){
        System.out.println(String.format(msg, params));
    }
}
