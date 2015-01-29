package com.polidea.injection;

import android.content.Context;
import android.os.AsyncTask;

public class NetworkManager {

    private ApiAccessor apiAccessor;

    private SendMessageCompletion sendMessageCompletion;

    public NetworkManager(Context context) {
        apiAccessor = new ApiAccessor(context);
    }

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
