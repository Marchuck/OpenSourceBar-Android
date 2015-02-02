package com.polidea.opensourcebar.rxsample.network;

import rx.Observable;

public class NetworkExample {

    private RestAdapter restAdapter;

    public Observable<Magazine> performMagazinesCacheUpdate() {
        return new MagazineListRequest().toObservable(restAdapter)
                .doOnNext(magazineList -> putInCache(magazineList.getCacheKey(), magazineList))
                .flatMap(magazineList -> Observable.from(magazineList.getCategories()))
                .map(category -> new MagazineRequest(category.getIdentifier()))
                .flatMap(apiCall -> apiCall.toObservable(restAdapter))
                .doOnNext(magazine -> putInCache(magazine.getCacheKey(), magazine));
    }

    private void putInCache(String cacheKey, Object objectToCache) {
        Log.log("Put %s under %s", objectToCache, cacheKey);
    }

}
