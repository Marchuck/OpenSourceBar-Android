package com.polidea.roboguice;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.inject.Inject;
import java.util.ArrayList;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_sample)
public class RoboGuiceSampleActivity extends RoboActionBarActivity {

    @InjectView(R.id.toolbar)
    private Toolbar toolbar;

    @InjectView(R.id.list_view)
    private ListView listView;

    @InjectView(R.id.text_view)
    private TextView textView;

    @InjectView(R.id.send_button)
    private ImageButton sendButton;

    @Inject
    Vibrator vibrator;

    @Inject
    NetworkManager networkManager;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                Toast.makeText(RoboGuiceSampleActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
                adapter.add(message);
            }

            @Override
            public void sendMessageFailure() {
                Toast.makeText(RoboGuiceSampleActivity.this, "Error while sending message", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
