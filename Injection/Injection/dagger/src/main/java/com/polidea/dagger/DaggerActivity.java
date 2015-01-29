package com.polidea.dagger;

import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import java.util.ArrayList;
import javax.inject.Inject;


public class DaggerActivity extends ActionBarActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.list_view)
    ListView listView;

    @InjectView(R.id.text_view)
    TextView textView;

    @InjectView(R.id.send_button)
    ImageButton sendButton;

    @Inject
    Vibrator vibrator;

    @Inject
    NetworkManager networkManager;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Application.component(this).inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        listView.setAdapter(adapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(textView.getText().toString());
            }
        });
    }

    private void sendMessage(String message) {
        if (message.length() == 0) {
            vibrator.vibrate(400);
            Toast.makeText(this, "Text view cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        networkManager.sendMessage(message, new NetworkManager.SendMessageCompletion() {
            @Override
            public void sendMessageSuccess(String message) {
                Toast.makeText(DaggerActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
                adapter.add(message);
            }

            @Override
            public void sendMessageFailure() {
                Toast.makeText(DaggerActivity.this, "Error while sending message", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
