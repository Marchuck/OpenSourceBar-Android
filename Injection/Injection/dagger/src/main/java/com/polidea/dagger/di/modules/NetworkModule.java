package com.polidea.dagger.di.modules;

import android.app.Application;
import com.polidea.dagger.ApiAccessor;
import com.polidea.dagger.NetworkManager;
import com.polidea.dagger.R;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class NetworkModule {

    private final Application application;

    public NetworkModule(Application application) {
        this.application = application;
    }

    @Provides
    NetworkManager provideNetworkManager() {
        return new NetworkManager(application);
    }

    @Singleton
    @Provides
    ApiAccessor provideApiAccessor() {
        return new ApiAccessor(application.getString(R.string.host_url));
    }
}
