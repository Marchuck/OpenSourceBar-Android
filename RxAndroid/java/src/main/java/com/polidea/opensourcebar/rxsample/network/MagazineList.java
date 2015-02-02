package com.polidea.opensourcebar.rxsample.network;

import java.util.Arrays;

public class MagazineList {

    public String getCacheKey() {
        return "cacheKeyMagazineList";
    }

    public Iterable<Category> getCategories() {
        return Arrays.asList(new Category(), new Category(), new Category());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
