package com.licj.wehome.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.licj.wehome.R;
import com.licj.wehome.bean.VRHomeBean;
import com.licj.wehome.commons.OnItemClickListener;

import java.util.List;

public class IndexVRHomeAdapter extends RecyclerView.Adapter<IndexVRHomeAdapter.IndexVRHomeHolder> {

    private Context context;
    private List<VRHomeBean> list;
    private OnItemClickListener mOnItemClickListener;//声明接口

    public IndexVRHomeAdapter(Context context, List<VRHomeBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public IndexVRHomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_vr_home, null);
        return new IndexVRHomeHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final IndexVRHomeHolder holder, int position) {
        holder.bindView(position);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class IndexVRHomeHolder extends RecyclerView.ViewHolder {

        private TextView tvItemVRHome;

        public IndexVRHomeHolder(@NonNull View itemView) {
            super(itemView);
            tvItemVRHome = itemView.findViewById(R.id.tv_item_vr_home_title);
        }

        public void bindView(int position) {
            String title = list.get(position).getTitle();
            tvItemVRHome.setText(title);

            int houseType = (int) (1 + Math.random() * 10);
            Drawable drawable;
            if (houseType % 3 == 0) {
                drawable = context.getResources().getDrawable(R.drawable.house1);
            } else if (houseType % 3 == 1) {
                drawable = context.getResources().getDrawable(R.drawable.house2);
            } else {
                drawable = context.getResources().getDrawable(R.drawable.house3);
            }

            tvItemVRHome.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
    }
}
