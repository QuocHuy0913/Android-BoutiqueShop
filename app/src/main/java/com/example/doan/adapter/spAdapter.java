package com.example.doan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.Utils;
import com.example.doan.sp;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class spAdapter extends RecyclerView.Adapter<spAdapter.spViewHolder>{
    ArrayList<sp>lstSP;
    Context context;
    spCallback spCallback;

    public spAdapter(ArrayList<sp> lstSP) {
        this.lstSP = lstSP;
    }

    public void setCallBack(spCallback spCallback){this.spCallback = spCallback;}

    @NonNull
    @Override
    public spViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View spView = inflater.inflate(R.layout.layout_item,parent,false);
        spViewHolder viewHolder = new spViewHolder(spView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull spViewHolder holder, int position) {

        // lấy từng item của dữ liệu
        sp item = lstSP.get(position);
        double number = item.getPrice();
        Locale localeUS = new Locale("en", "US");
        NumberFormat currencyUS = NumberFormat.getCurrencyInstance(localeUS);
        String str = currencyUS.format(number);

        // gán item vào view
        holder.imgPro.setImageBitmap(Utils.convertToBitmapFromAssets(context,item.getResourceImage()));
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(str);
        holder.tvDescription.setText(item.getDescription());
        holder.tvCategory.setText(item.getCategory());
        // bắt sự kiện
        holder.ivEdit.setOnClickListener(view -> spCallback.onItemEditClicked(item, position));
        holder.ivDelete.setOnClickListener(view -> spCallback.onItemDeleteCLicked(item, position));
    }

    @Override
    public int getItemCount() {
        return lstSP.size();
    }

    static class spViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPro;
        TextView tvName;
        TextView tvPrice;
        TextView tvDescription;
        TextView tvCategory;
        ImageView ivEdit;
        ImageView ivDelete;
        public spViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPro = itemView.findViewById(R.id.Image_Pro);
            tvName = itemView.findViewById(R.id.tv_NamePro);
            tvPrice = itemView.findViewById(R.id.tv_PricePro);
            tvDescription = itemView.findViewById(R.id.tv_DesPro);
            tvCategory = itemView.findViewById(R.id.tv_CatePro);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }

    // dùng để thực hiện thao tác xóa, cập nhật trên dòng
    public interface spCallback{
        void onItemDeleteCLicked(sp sanpham,int position);
        void onItemEditClicked(sp sanpham,int position);
    }
}
