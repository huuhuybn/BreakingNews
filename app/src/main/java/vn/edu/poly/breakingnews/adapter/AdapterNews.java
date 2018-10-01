package vn.edu.poly.breakingnews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.edu.poly.breakingnews.R;
import vn.edu.poly.breakingnews.holder.HolderNews;
import vn.edu.poly.breakingnews.model.News;

public class AdapterNews extends RecyclerView.Adapter<HolderNews> {

    private Context context;

    private List<News> news;

    public AdapterNews(Context context, List<News> news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public HolderNews onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new HolderNews(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNews holder, int position) {
        News itemNews = news.get(position);
        holder.tvTitle.setText(itemNews.title);
        holder.tvDate.setText(itemNews.pubDate);
        holder.tvDes.setText(itemNews.description);


        // load image to ImageView by Glide library
        Glide.with(context).load(itemNews.link).into(holder.imgThumbs);


    }

    @Override
    public int getItemCount() {
        if (news == null) return 0;
        return news.size();
    }
}
