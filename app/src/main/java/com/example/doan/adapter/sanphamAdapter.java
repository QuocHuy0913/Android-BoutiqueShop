package com.example.doan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.Sanpham;
import com.example.doan.Utils;
import com.example.doan.my_interface.IClickItemSPListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class sanphamAdapter extends RecyclerView.Adapter<sanphamAdapter.SanPhamViewhHolder> {
    IClickItemSPListener iClickItemSPListener;
    ArrayList<Sanpham> lstSanpham;
    Context context;

    public sanphamAdapter(ArrayList<Sanpham> lstSanpham, IClickItemSPListener iClickItemSPListener) {
        this.lstSanpham = lstSanpham;
        this.iClickItemSPListener = iClickItemSPListener;
    }

    public sanphamAdapter(Context mContext){
        this.context = mContext;
    }
    public void setData(ArrayList<Sanpham> list)
    {
        this.lstSanpham = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SanPhamViewhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent, false);
        return new SanPhamViewhHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewhHolder holder, int position) {
        final Sanpham sanpham = lstSanpham.get(position);
        if(sanpham == null)
        {
            return;
        }
        double number = sanpham.getPrice();
        Locale localeUS = new Locale("en", "US");
        NumberFormat currencyUS = NumberFormat.getCurrencyInstance(localeUS);
        String str = currencyUS.format(number);

        holder.imgSanPham.setImageBitmap(Utils.convertToBitmapFromAssets(context, sanpham.getResourceImage()));
        holder.tvTenSP.setText(sanpham.getName());
        holder.tvGiaSp.setText(str);

        holder.layoutSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemSPListener.onClickItemSP(sanpham);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(lstSanpham != null)
        {
            return lstSanpham.size();
        }
        return 0;
    }

    public class SanPhamViewhHolder extends RecyclerView.ViewHolder{
        private CardView layoutSP;
        private ImageView imgSanPham;
        private TextView tvTenSP;
        private TextView tvGiaSp;

        public SanPhamViewhHolder(@NonNull View itemView) {
            super(itemView);
            layoutSP = itemView.findViewById(R.id.layout_SP);
            imgSanPham = itemView.findViewById(R.id.img_sanpham);
            tvTenSP = itemView.findViewById(R.id.tv_tensp);
            tvGiaSp = itemView.findViewById(R.id.tv_giasp);
        }
    }
}
