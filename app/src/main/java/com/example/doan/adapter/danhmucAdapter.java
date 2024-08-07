package com.example.doan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Danhmuc;
import com.example.doan.R;

import java.util.List;

public class danhmucAdapter extends RecyclerView.Adapter<danhmucAdapter.DanhMucViewhHolder> {
    private Context mContext;
    private List<Danhmuc> mListDM;

    public danhmucAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Danhmuc> list)
    {
        this.mListDM = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DanhMucViewhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhmuc, parent, false);
        return new DanhMucViewhHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucViewhHolder holder, int position) {
        Danhmuc danhmuc = mListDM.get(position);
        if(danhmuc == null)
        {
            return;
        }
        holder.imgDanhMuc.setImageResource(danhmuc.getResourceImage());
        holder.tvTenDM.setText(danhmuc.getName());
    }


    @Override
    public int getItemCount() {
        if(mListDM != null)
        {
            return mListDM.size();
        }
        return 0;
    }

    public class DanhMucViewhHolder extends RecyclerView.ViewHolder{
        private ImageView imgDanhMuc;
        private TextView tvTenDM;

        public DanhMucViewhHolder(@NonNull View itemView) {
            super(itemView);
            imgDanhMuc = itemView.findViewById(R.id.img_danhmuc);
            tvTenDM = itemView.findViewById(R.id.tv_tendanhmuc);
        }
    }
}
