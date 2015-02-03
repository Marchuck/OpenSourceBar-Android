package com.polidea.getditlibs;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.polidea.getditlibs.model.Gif;
import com.polidea.getditlibs.model.RChildren;
import com.polidea.getditlibs.model.RResponse;
import com.polidea.getditlibs.network.RedditRequest;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends SpiceActivity implements RequestListener<RResponse> {

    GifsAdapter gifsAdapter;

    List<Gif> gifs = new ArrayList<>();

    RedditRequest redditRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.list);
        gifsAdapter = new GifsAdapter(this, R.layout.gif_item, gifs);
        lv.setAdapter(gifsAdapter);
        redditRequest = new RedditRequest();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSpiceManager().execute(redditRequest, this);
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestSuccess(RResponse gifList) {
        gifs.clear();
        for (RChildren gif : gifList.getData().getGifs()) {
            gifs.add(gif.getGif());
        }
        gifsAdapter.notifyDataSetChanged();
    }
}
