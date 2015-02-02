package com.polidea.opensourcebar.rxsample.network;

import org.junit.Test;

public class NetworkExampleTest {

    @Test
    public void testPerformMagazinesCacheUpdate() throws Exception {
        NetworkExample networkExample = new NetworkExample();
        networkExample.performMagazinesCacheUpdate()
                .subscribe(
                        magazine -> System.out.println("New magazine arrived!"),
                        throwable -> {
                            System.err.println("Error");
                            throwable.printStackTrace();
                        },
                        () -> System.out.println("Completed!"));
    }
}