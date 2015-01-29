package com.polidea.injection;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class ManualActivity extends ActionBarActivity {

    private Toolbar toolbar;

    private ListView listView;

    private TextView textView;

    private ImageButton sendButton;

    private Vibrator vibrator;

    private NetworkManager networkManager;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.list_view);
        textView = (TextView) findViewById(R.id.text_view);
        sendButton = (ImageButton) findViewById(R.id.send_button);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        networkManager = new NetworkManager(this);

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
                Toast.makeText(ManualActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
                adapter.add(message);
            }

            @Override
            public void sendMessageFailure() {
                Toast.makeText(ManualActivity.this, "Error while sending message", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
