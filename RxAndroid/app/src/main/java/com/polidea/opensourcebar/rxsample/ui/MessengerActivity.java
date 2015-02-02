package com.polidea.opensourcebar.rxsample.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.polidea.opensourcebar.rxsample.R;
import java.util.ArrayList;
import java.util.List;

public class MessengerActivity extends ActionBarActivity implements View.OnClickListener {

    private static final int MAX_BODY_LENGTH = 200;

    @InjectView(R.id.id_et_phone)
    EditText phoneEditText;

    @InjectView(R.id.id_et_message)
    EditText messageEditText;

    @InjectView(R.id.id_tv_remaining_chars)
    TextView remainingCharsTextView;

    @InjectView(R.id.id_btn_send)
    Button sendMessageButton;

    @InjectView(R.id.id_rv_messages)
    RecyclerView messagesRecyclerView;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private List<MessageModel> messages = new ArrayList<>();

    private MessagesAdapter adapter = new MessagesAdapter(messages);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        sendMessageButton.setEnabled(false);
        remainingCharsTextView.setText(getString(R.string.remaining_chars, MAX_BODY_LENGTH));

        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messagesRecyclerView.setHasFixedSize(true);
        messagesRecyclerView.setAdapter(adapter);

        MessageEditTextChangeListener messageEditTextWatcher = new MessageEditTextChangeListener();

        messageEditText.addTextChangedListener(messageEditTextWatcher);

        sendMessageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(TextUtils.isEmpty(phoneEditText.getText())){
            phoneEditText.requestFocus();
        }else{
            flushMessageInput();
            focusTo(messageEditText);
            MessageModel message = new MessageModel(phoneEditText.getText(), messageEditText.getText());
            messages.add(message);
            adapter.notifyDataSetChanged();
            messagesRecyclerView.scrollToPosition(messages.size() - 1);
        }
    }

    private class MessageEditTextChangeListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean messageInputNotEmpty = !TextUtils.isEmpty(messageEditText.getText().toString());
            sendMessageButton.setEnabled(messageInputNotEmpty);

            int remainingChars = MAX_BODY_LENGTH - messageEditText.getText().length();
            remainingCharsTextView.setText(getString(R.string.remaining_chars, remainingChars));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    }

    private void flushMessageInput() {
        messageEditText.setText("");
    }

    private void focusTo(View viewToRequestFocus) {
        viewToRequestFocus.requestFocus();
    }
}