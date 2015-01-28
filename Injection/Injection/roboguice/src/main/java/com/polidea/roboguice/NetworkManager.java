package com.polidea.roboguice;

import android.content.Context;
import android.os.AsyncTask;
import javax.inject.Inject;
import roboguice.inject.ContextSingleton;

@ContextSingleton
public class NetworkManager {

    @Inject
    ApiAccessor apiAccessor;

    @Inject
    Context context;

    private SendMessageCompletion sendMessageCompletion;

    public void sendMessage(String message, SendMessageCompletion completionRunnable) {
        sendMessageCompletion = completionRunnable;
        new SendMessageTask().execute(message);
    }

    private class SendMessageTask extends AsyncTask<String, Integer, Boolean> {

        private String message;

        @Override
        protected Boolean doInBackground(String... params) {
            message = params[0];
            return apiAccessor.sendMessage(message);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                sendMessageCompletion.sendMessageSuccess(message);
            } else {
                sendMessageCompletion.sendMessageFailure();
            }
        }
    }

    public interface SendMessageCompletion {

        void sendMessageSuccess(String message);

        void sendMessageFailure();
    }
}
