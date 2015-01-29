package com.polidea.dagger;

import android.content.Context;
import com.polidea.dagger.di.ApplicationComponent;

public class Application extends android.app.Application {

    private ApplicationComponent component;

    public static ApplicationComponent component(Context context) {
        return ((Application) context.getApplicationContext()).component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        buildComponentAndInject();
    }

    public void buildComponentAndInject() {
        component = ApplicationComponent.Initializer.init(this);
    }
}
