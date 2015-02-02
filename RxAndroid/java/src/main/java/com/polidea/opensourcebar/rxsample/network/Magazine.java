package com.polidea.opensourcebar.rxsample.network;

public class Magazine {

    public String getCacheKey() {
        return "magazineCacheKey";
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
