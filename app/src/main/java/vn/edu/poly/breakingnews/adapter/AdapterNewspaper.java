package vn.edu.poly.breakingnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.edu.poly.breakingnews.ListNewsActivity;
import vn.edu.poly.breakingnews.R;
import vn.edu.poly.breakingnews.holder.HolderNewspaper;
import vn.edu.poly.breakingnews.model.Newspaper;

public class AdapterNewspaper extends RecyclerView.Adapter<HolderNewspaper> {

    private Context context;

    private List<Newspaper> newspapers;

    public AdapterNewspaper(Context context, List<Newspaper> newspapers) {
        this.context = context;
        this.newspapers = newspapers;
    }

    @NonNull
    @Override
    public HolderNewspaper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_newspaper, parent,
                false);
        return new HolderNewspaper(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNewspaper holder, int position) {
        final Newspaper newspaper = newspapers.get(position);
        holder.tvTitle.setText(newspaper.name);

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ListNewsActivity.class);
                intent.putExtra("data",newspaper.rssLink);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (newspapers == null) return 0;
        return newspapers.size();
    }
}
