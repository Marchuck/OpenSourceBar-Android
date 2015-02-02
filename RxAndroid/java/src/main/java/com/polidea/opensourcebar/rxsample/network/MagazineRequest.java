package com.polidea.opensourcebar.rxsample.network;

import rx.Observable;

public class MagazineRequest {

    public MagazineRequest(Object identifier) {
    }

    public Observable<Magazine> toObservable(RestAdapter restAdapter) {
        return Observable.just(new Magazine());
    }
}
