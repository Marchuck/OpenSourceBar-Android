package com.polidea.dagger.di;

import android.app.Application;
import com.polidea.dagger.DaggerActivity;
import com.polidea.dagger.NetworkManager;
import com.polidea.dagger.di.modules.NetworkModule;
import com.polidea.dagger.di.modules.SystemServiceModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkModule.class, SystemServiceModule.class})
public interface ApplicationComponent {

    void inject(DaggerActivity daggerActivity);

    void inject(NetworkManager networkManager);

    public final static class Initializer {

        private Initializer() {
        }

        public static ApplicationComponent init(Application app) {
            return Dagger_ApplicationComponent.builder()
                    .systemServiceModule(new SystemServiceModule(app))
                    .networkModule(new NetworkModule(app))
                    .build();
        }
    }
}
