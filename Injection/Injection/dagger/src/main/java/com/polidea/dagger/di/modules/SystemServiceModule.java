package com.polidea.dagger.di.modules;

import android.app.Application;
import android.content.Context;
import android.os.Vibrator;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class SystemServiceModule {

    private final Application application;

    public SystemServiceModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Vibrator provideVibrator() {
        return (Vibrator) application.getSystemService(Context.VIBRATOR_SERVICE);
    }
}
