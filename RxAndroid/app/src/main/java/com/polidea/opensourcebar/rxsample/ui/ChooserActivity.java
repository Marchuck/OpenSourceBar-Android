package com.polidea.opensourcebar.rxsample.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.polidea.opensourcebar.rxsample.R;

public class ChooserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.id_btn_standard)
    void onStandardClick(){
        openActivity(MessengerActivity.class);
    }

    @OnClick(R.id.id_btn_rx)
    void onRxClick(){
        openActivity(RxMessengerActivity.class);
    }

    @OnClick(R.id.id_btn_rx_retrolambda)
    void onRxRetrolambdaClick(){
        openActivity(RxRetrolambdaMessengerActivity.class);
    }

    private void openActivity(Class<? extends Activity> activityClass) {
        startActivity(new Intent(this, activityClass));
    }
}
