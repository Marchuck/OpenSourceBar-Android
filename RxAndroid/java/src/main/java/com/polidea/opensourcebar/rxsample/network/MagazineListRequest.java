package com.polidea.opensourcebar.rxsample.network;

import rx.Observable;

public class MagazineListRequest {

    public MagazineListRequest() {
    }

    public Observable<MagazineList> toObservable(RestAdapter restAdapter) {
        return Observable.just(new MagazineList());
    }
}
