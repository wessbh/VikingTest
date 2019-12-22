package com.wassimbh.vikingtest.utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wassimbh.vikingtest.R;
import com.wassimbh.vikingtest.models.Article;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private List<Article> articleList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView subtitle;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            subtitle = view.findViewById(R.id.subtitle);
            img = view.findViewById(R.id.img);
        }
    }


    public RecyclerAdapter(Context context, List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyler_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Article article = articleList.get(position);
        holder.name.setText(article.getName());
        holder.subtitle.setText(article.getSubtitle());
        Picasso.get().load(article.getThumbnail()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
