package com.example.doan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Gioithieu;
import com.example.doan.R;

import java.util.List;

public class gioithieuAdapter extends RecyclerView.Adapter<gioithieuAdapter.gioithieuViewHolder> {

    private Context mContext;
    private List<Gioithieu> mListGioiThieu;

    public gioithieuAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Gioithieu> list)
    {
        this.mListGioiThieu = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public gioithieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gioithieu_linear, parent, false);
        return new gioithieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull gioithieuViewHolder holder, int position) {
        Gioithieu gioithieu = mListGioiThieu.get(position);
        if(gioithieu == null)
        {
            return;
        }
        holder.imgGioiThieu.setImageResource(gioithieu.getResourceId());
        holder.tvHeader.setText(gioithieu.getHeader());
        holder.tvContent.setText(gioithieu.getContent());
    }

    @Override
    public int getItemCount() {
        if(mListGioiThieu != null)
        {
            return mListGioiThieu.size();
        }
        return 0;
    }

    public class gioithieuViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgGioiThieu;
        private TextView tvHeader;
        private TextView tvContent;

        public gioithieuViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGioiThieu = itemView.findViewById(R.id.img_gioithieu);
            tvHeader = itemView.findViewById(R.id.tv_hearder_content);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
