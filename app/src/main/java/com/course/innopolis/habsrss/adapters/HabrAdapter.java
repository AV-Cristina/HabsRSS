package com.course.innopolis.habsrss.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.course.innopolis.habsrss.app.OnListItemCallback;
import com.course.innopolis.habsrss.models.HabrArticle;
import com.course.innopolis.habsrss.R;

import java.util.List;

/**
 * Created by Cristina on 21.07.2017.
 *
 * Адаптер для вывода списка элементов HabrArticle в RecyclerView
 */

public class HabrAdapter extends RecyclerView.Adapter<HabrAdapter.HabrHolder> {

    private List<HabrArticle> mHabsList;
    private LayoutInflater mInflater;
    private OnListItemCallback callback;

    public HabrAdapter(Context context, List<HabrArticle> mHabsList, OnListItemCallback callback) {
        this.mHabsList = mHabsList;
        this.mInflater = LayoutInflater.from(context);
        this.callback = callback;
    }

    @Override
    public HabrHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.hab_item, parent, false);
        HabrHolder holder = new HabrHolder(view, callback);
        return holder;
    }

    @Override
    public void onBindViewHolder(HabrHolder holder, int position) {
        HabrArticle currentHab = mHabsList.get(position);
        holder.setData(currentHab, position);
    }

    @Override
    public int getItemCount() {
        return mHabsList.size();
    }


    class HabrHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView pubDate, creator, title;
        int position;
        HabrArticle current;
        private OnListItemCallback callback;

        public HabrHolder(View itemView, OnListItemCallback callback) {
            super(itemView);
            pubDate = (TextView)itemView.findViewById(R.id.hab_pubDate);
            creator = (TextView)itemView.findViewById(R.id.had_creator);
            title = (TextView)itemView.findViewById(R.id.hab_title);
            this.callback= callback;
            itemView.setOnClickListener(this);
        }

        public void setData(HabrArticle current, int position) {
            this.pubDate.setText(current.getPubDate());
            this.creator.setText(current.getCreator());
            this.title.setText(current.getTitle());
            this.position = position;
            this.current = current;
        }

        @Override
        public void onClick(View v) {
            callback.onClick(current);
        }
    }
}
