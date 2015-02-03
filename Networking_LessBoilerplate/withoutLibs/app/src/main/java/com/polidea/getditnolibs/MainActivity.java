package com.polidea.getditnolibs;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import com.polidea.getditnolibs.model.Gif;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    private static String URL = "http://www.reddit.com/r/gifs/.json?limit=25";

    List<Gif> gifs = new ArrayList<>();

    GifsAdapter gifsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.list);
        gifsAdapter = new GifsAdapter(this, R.layout.gif_item, gifs);
        lv.setAdapter(gifsAdapter);
        new JSONParse().execute(URL);
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = jsonParser.getJSON(URL);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            progressDialog.dismiss();
            try {
                gifs.clear();
                JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("children");
                for (int i = 0; i < jsonArray.length(); i++) {
                    Gif tmpGif = new Gif();

                    tmpGif.setTitle(jsonArray.getJSONObject(i).getJSONObject("data").getString("title"));
                    tmpGif.setUrl(jsonArray.getJSONObject(i).getJSONObject("data").getString("url"));
                    tmpGif.setThumbnail(jsonArray.getJSONObject(i).getJSONObject("data").getString("thumbnail"));
                    tmpGif.setPermalink(jsonArray.getJSONObject(i).getJSONObject("data").getString("permalink"));
                    gifs.add(tmpGif);
                }
                gifsAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
