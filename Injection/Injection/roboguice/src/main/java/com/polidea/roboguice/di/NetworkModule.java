package com.polidea.roboguice.di;

import android.app.Application;
import android.content.Context;
import com.google.inject.AbstractModule;
import com.polidea.roboguice.ApiAccessor;
import com.polidea.roboguice.R;

public class NetworkModule extends AbstractModule {

    private Context context;

    public NetworkModule(Application context) {
        this.context = context;
    }

    @Override
    protected void configure() {
        bind(ApiAccessor.class).toInstance(new ApiAccessor(context.getString(R.string.host_url)));
    }
}
