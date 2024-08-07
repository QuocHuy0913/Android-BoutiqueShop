package com.example.doan.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.EventBus.TinhTongEvent;
import com.example.doan.Giohang;
import com.example.doan.R;
import com.example.doan.Utils;
import com.example.doan.Yeuthich;
import com.example.doan.my_interface.IImageClickListenner;
import com.example.doan.utils.Utilsss;

import org.greenrobot.eventbus.EventBus;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class yeuthichAdapter extends RecyclerView.Adapter<yeuthichAdapter.MyViewHolder> {
    Context context;
    List<Yeuthich> yeuthichList;

    public yeuthichAdapter(Context context, List<Yeuthich> yeuthichList) {
        this.context = context;
        this.yeuthichList = yeuthichList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_yeuthich_image, delete_yeuthich;
        TextView item_yeuthich_tensp, item_yeuthich_gia, item_giohang_masp;
        IImageClickListenner listenner;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_yeuthich_image = itemView.findViewById(R.id.item_yeuthich_image);
            item_yeuthich_tensp = itemView.findViewById(R.id.item_yeuthich_tensp);
            item_yeuthich_gia = itemView.findViewById(R.id.item_yeuthich_gia);
            item_giohang_masp = itemView.findViewById(R.id.item_yeuthich_masp);
            delete_yeuthich = itemView.findViewById(R.id.delete_yeuthich);

            //event click
            delete_yeuthich.setOnClickListener(this);
        }

        public void setListenner(IImageClickListenner listenner) {
            this.listenner = listenner;
        }

        @Override
        public void onClick(View v) {
            if(v == delete_yeuthich){
                listenner.onImageClick(v, getAdapterPosition(), 1);
            }
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yeuthich, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull yeuthichAdapter.MyViewHolder holder, int position) {
        Yeuthich yeuthich = yeuthichList.get(position);
        holder.item_yeuthich_tensp.setText(yeuthich.getTensp());
        holder.item_yeuthich_image.setImageBitmap(Utils.convertToBitmapFromAssets(context, yeuthich.getHinhsp()));
        holder.item_giohang_masp.setText(String.valueOf(yeuthich.getIdsp()));

        double number = yeuthich.getGiasp();
        Locale localeUS = new Locale("en", "US");
        NumberFormat currencyUS = NumberFormat.getCurrencyInstance(localeUS);
        String str = currencyUS.format(number);

        holder.item_yeuthich_gia.setText(str);

        holder.setListenner(new IImageClickListenner() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                if(giatri == 1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn xóa sản phẩm này khỏi yêu thích?");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utilsss.mangyeuthich.remove(pos);
                            notifyDataSetChanged();
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
        return yeuthichList.size();
    }
}
