package com.polidea.getditlibs;

import android.support.v7.app.ActionBarActivity;
import com.octo.android.robospice.SpiceManager;
import com.polidea.getditlibs.network.RetrofitSpiceService;

public class SpiceActivity extends ActionBarActivity{

    private SpiceManager spiceManager = new SpiceManager(RetrofitSpiceService.class);

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }

}
