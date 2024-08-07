package com.example.doan.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan.EventBus.TinhTongEvent;
import com.example.doan.Giohang;
import com.example.doan.R;
import com.example.doan.Utils;
import com.example.doan.my_interface.IImageClickListenner;
import com.example.doan.utils.Utilsss;

import org.greenrobot.eventbus.EventBus;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHolder> {
    Context context;
    List<Giohang> giohangList;


    public GioHangAdapter(Context context, List<Giohang> giohangList) {
        this.context = context;
        this.giohangList = giohangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Giohang giohang = giohangList.get(position);
        holder.item_giohang_tensp.setText(giohang.getTensp());
        holder.item_giohang_soluong.setText(giohang.getSoluong() + " ");
        holder.item_giohang_image.setImageBitmap(Utils.convertToBitmapFromAssets(context, giohang.getHinhsp()));
        holder.item_giohang_masp.setText(String.valueOf(giohang.getIdsp()));

        double number = giohang.getGiasp();
        Locale localeUS = new Locale("en", "US");
        NumberFormat currencyUS = NumberFormat.getCurrencyInstance(localeUS);
        String str = currencyUS.format(number);

        holder.item_giohang_gia.setText(str);

        double gia = giohang.getSoluong() * giohang.getGiasp();
        String str2 = currencyUS.format(gia);
        holder.item_giohang_giasp2.setText(str2);

        holder.setListenner(new IImageClickListenner() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                Log.d("TAG", "onImageClick: " + pos + "..." + giatri);
                if(giatri == 1){
                    if(giohangList.get(pos).getSoluong() > 1){
                        int soluongmoi = giohangList.get(pos).getSoluong() - 1;
                        giohangList.get(pos).setSoluong(soluongmoi);

                        holder.item_giohang_soluong.setText(giohangList.get(pos).getSoluong() + " ");

                        double gia = giohangList.get(pos).getSoluong() * giohangList.get(pos).getGiasp();
                        String str2 = currencyUS.format(gia);
                        holder.item_giohang_giasp2.setText(str2);
                        EventBus.getDefault().postSticky(new TinhTongEvent());
                    }
                }
                else if (giatri == 2) {
                    if(giohangList.get(pos).getSoluong() < 11){
                        int soluongmoi = giohangList.get(pos).getSoluong() + 1;
                        giohangList.get(pos).setSoluong(soluongmoi);
                    }
                    holder.item_giohang_soluong.setText(giohangList.get(pos).getSoluong() + " ");

                    double gia = giohangList.get(pos).getSoluong() * giohangList.get(pos).getGiasp();
                    String str2 = currencyUS.format(gia);
                    holder.item_giohang_giasp2.setText(str2);
                    EventBus.getDefault().postSticky(new TinhTongEvent());
                } else if (giatri == 3) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng?");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utilsss.manggiohang.remove(pos);
                            notifyDataSetChanged();
                            EventBus.getDefault().postSticky(new TinhTongEvent());
                        }
                    });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return giohangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_giohang_image, imgtru, imgcong, delete_giohang;
        TextView item_giohang_tensp, item_giohang_gia, item_giohang_soluong, item_giohang_giasp2, item_giohang_masp;
        IImageClickListenner listenner;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_giohang_image = itemView.findViewById(R.id.item_giohang_image);
            item_giohang_tensp = itemView.findViewById(R.id.item_giohang_tensp);
            item_giohang_gia = itemView.findViewById(R.id.item_giohang_gia);
            item_giohang_soluong = itemView.findViewById(R.id.item_giohang_soluong);
            item_giohang_giasp2 = itemView.findViewById(R.id.item_giohang_giasp2);
            item_giohang_masp = itemView.findViewById(R.id.item_giohang_masp);
            imgtru = itemView.findViewById(R.id.item_giohang_tru);
            imgcong = itemView.findViewById(R.id.item_giohang_cong);
            delete_giohang = itemView.findViewById(R.id.delete_giohang);

            // event click
            imgtru.setOnClickListener(this);
            imgcong.setOnClickListener(this);
            delete_giohang.setOnClickListener(this);
        }

        public void setListenner(IImageClickListenner listenner) {
            this.listenner = listenner;
        }

        @Override
        public void onClick(View v) {
            if(v == imgtru){
                listenner.onImageClick(v, getAdapterPosition(), 1);
                // 1 tru
            } else if (v == imgcong) {
                // 2 cong
                listenner.onImageClick(v, getAdapterPosition(), 2);
            }
            else if(v == delete_giohang){
                // 3 delete
                listenner.onImageClick(v, getAdapterPosition(), 3);
            }
        }
    }
}
