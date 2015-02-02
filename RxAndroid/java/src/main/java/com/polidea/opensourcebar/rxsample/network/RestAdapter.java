package com.polidea.opensourcebar.rxsample.network;

import rx.Observable;

public class RestAdapter {

    public Observable<MagazineList> callEndpoint() {
        return Observable.just(new MagazineList());
    }
}
