package com.polidea.opensourcebar.rxsample.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.polidea.opensourcebar.rxsample.R;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor(suppressConstructorProperties = true)
public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private List<MessageModel> messages;

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_message, parent, false);
        return new MessageViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.messageBodyTextView.setText(messages.get(position).getMessageBody());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.id_tv_message_body)
        public TextView messageBodyTextView;

        MessageViewHolder(View inflatedView) {
            super(inflatedView);
            ButterKnife.inject(this, inflatedView);
        }
    }
}
