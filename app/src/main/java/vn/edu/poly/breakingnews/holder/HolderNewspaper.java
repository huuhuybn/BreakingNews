package vn.edu.poly.breakingnews.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vn.edu.poly.breakingnews.R;

public class HolderNewspaper extends RecyclerView.ViewHolder {



    public final TextView tvTitle;

    public HolderNewspaper(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);

    }
}
