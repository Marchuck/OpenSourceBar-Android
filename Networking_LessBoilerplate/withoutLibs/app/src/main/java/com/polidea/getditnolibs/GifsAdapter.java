package com.polidea.getditnolibs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.polidea.getditnolibs.model.Gif;
import com.squareup.picasso.Picasso;
import java.util.List;

public class GifsAdapter extends ArrayAdapter<Gif> {

    Context context;

    int resource;

    List<Gif> gifs;

    public GifsAdapter(Context context, int resource, List<Gif> gifs) {
        super(context, resource, gifs);
        this.context = context;
        this.resource = resource;
        this.gifs = gifs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = View.inflate(context, resource, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.url = (TextView) view.findViewById(R.id.url);
            viewHolder.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            viewHolder.permalink = (TextView) view.findViewById(R.id.permalink);
            view.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        Gif tmpGif = gifs.get(position);
        holder.title.setText(tmpGif.getTitle());
        holder.url.setText(tmpGif.getUrl());
        holder.permalink.setText(tmpGif.getPermalink());
        if (tmpGif.getUrl() != null && tmpGif.getUrl().substring(tmpGif.getUrl().length() - 3, tmpGif.getUrl().length()).equalsIgnoreCase("gif")) {
            Picasso.with(context).load(tmpGif.getUrl()).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(holder.thumbnail);
        } else {
            holder.thumbnail.setImageResource(R.drawable.ic_launcher);
        }

        return view;
    }


    static class ViewHolder {

        public TextView title;

        public TextView url;

        public TextView permalink;

        public ImageView thumbnail;
    }
}
