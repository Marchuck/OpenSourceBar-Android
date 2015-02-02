package com.polidea.opensourcebar.rxsample.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.polidea.opensourcebar.rxsample.R;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.android.view.OnClickEvent;
import rx.android.view.ViewActions;
import rx.android.view.ViewObservable;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

//Based on https://github.com/andrewhr/rxjava-android-example with modifications
public class RxMessengerActivity extends ActionBarActivity {

    private static final int MAX_BODY_LENGTH = 200;

    private static final boolean EMIT_INITIAL_VALUE = true;

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

        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messagesRecyclerView.setHasFixedSize(true);
        messagesRecyclerView.setAdapter(adapter);

        final Observable<OnTextChangeEvent> phoneNumberObservable = ViewObservable.bindView(phoneEditText, WidgetObservable.text(phoneEditText, EMIT_INITIAL_VALUE));
        final Observable<OnTextChangeEvent> messageBodyObservable = ViewObservable.bindView(messageEditText, WidgetObservable.text(messageEditText, EMIT_INITIAL_VALUE));
        final Observable<OnClickEvent> sendMessageClick = ViewObservable.clicks(sendMessageButton, EMIT_INITIAL_VALUE);

        messageBodyObservable
                .map(new Func1<OnTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(OnTextChangeEvent inputEvent) {
                        return !TextUtils.isEmpty(inputEvent.text());
                    }
                })
                .subscribe(ViewActions.setEnabled(sendMessageButton));

        messageBodyObservable
                .map(new Func1<OnTextChangeEvent, Integer>() {
                    @Override
                    public Integer call(OnTextChangeEvent text) {
                        return MAX_BODY_LENGTH - text.text().length();
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer remainingChars) {
                        return getString(R.string.remaining_chars, remainingChars, MAX_BODY_LENGTH);
                    }
                })
                .subscribe(ViewActions.setText(remainingCharsTextView));

        sendMessageClick
                .flatMap(new Func1<OnClickEvent, Observable<? extends MessageModel>>() {
                    @Override
                    public Observable<? extends MessageModel> call(OnClickEvent clickEvent) {
                        return Observable.combineLatest(phoneNumberObservable, messageBodyObservable,
                                new Func2<OnTextChangeEvent, OnTextChangeEvent, MessageModel>() {
                                    @Override
                                    public MessageModel call(OnTextChangeEvent lastPhoneInputEvent, OnTextChangeEvent lastMessageInputEvent) {
                                        return new MessageModel(lastPhoneInputEvent.text(), lastMessageInputEvent.text());
                                    }
                                }).take(1);
                    }
                })
                .subscribe(new Action1<MessageModel>() {
                    @Override
                    public void call(MessageModel message) {
                        if (TextUtils.isEmpty(message.getPhoneNumber())) {
                            RxMessengerActivity.this.focusTo(phoneEditText);
                        } else {
                            RxMessengerActivity.this.flushMessageInput();
                            RxMessengerActivity.this.focusTo(messageEditText);
                            messages.add(message);
                            adapter.notifyDataSetChanged();
                            messagesRecyclerView.scrollToPosition(messages.size() - 1);
                        }
                    }
                });
    }

    private void flushMessageInput() {
        messageEditText.setText("");
    }

    private void focusTo(View viewToRequestFocus) {
        viewToRequestFocus.requestFocus();
    }
}