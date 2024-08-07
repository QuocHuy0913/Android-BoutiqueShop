package com.example.doan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doan.adapter.spAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity implements com.example.doan.adapter.spAdapter.spCallback{
    RecyclerView rvListCode;
    ArrayList<sp> lstSP;
    spAdapter spAdapter;
    FloatingActionButton fbAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        rvListCode = findViewById(R.id.rvList);
        fbAdd = findViewById(R.id.fbAdd);
        fbAdd.setOnClickListener(view -> addSPDialog());

        lstSP = spDataQuery.getAll(this);
        spAdapter = new spAdapter(lstSP);
        spAdapter.setCallBack(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListCode.setAdapter(spAdapter);
        rvListCode.setLayoutManager(linearLayoutManager);
    }

    void addSPDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminActivity.this);
        alertDialog.setTitle("Thêm mới");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_product,null);
        alertDialog.setView(dialogView);

        EditText edAvatar = (EditText) dialogView.findViewById(R.id.edImage_Pro);
        EditText edName = (EditText) dialogView.findViewById(R.id.edName_Pro);
        EditText edPrice = (EditText) dialogView.findViewById(R.id.edPrice_Pro);
        EditText edDescription = (EditText) dialogView.findViewById(R.id.edDescription_Pro);
        EditText edCategory = (EditText) dialogView.findViewById(R.id.edCategory_Pro);
        
        
        alertDialog.setPositiveButton("Đồng ý",(dialog,which)->{
            String avatar = edAvatar.getText().toString();
            String name = edName.getText().toString();
            String price = edPrice.getText().toString();
            String description = edDescription.getText().toString();
            String category = edCategory.getText().toString();


            System.out.println(avatar + " -- " + name + " -- " + price + " -- " + description + " -- " + category);


            if(name.isEmpty() || avatar.isEmpty() || description.isEmpty() || price.isEmpty())
            {
                Toast.makeText(this, "Nhập dữ liệu không hợp lệ ", Toast.LENGTH_LONG).show();
            }
            else
            {
                sp sanpham = new sp(0, avatar, name, Double.parseDouble(price), description, category, 0);
                long id = spDataQuery.insert(AdminActivity.this, sanpham);
                if(id > 0)
                    Toast.makeText(AdminActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_LONG).show();
                resetData();
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("Hủy",(dialog,which)->{
            dialog.dismiss();
        });
        alertDialog.create();
        alertDialog.show();
    }

    void updateUserDialog(sp sanpham)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminActivity.this);

        alertDialog.setTitle("Cập nhật");

        LayoutInflater inflater = this.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_add_product,null);

        alertDialog.setView(dialogView);

        EditText edAvatar = (EditText) dialogView.findViewById(R.id.edImage_Pro);
        EditText edName = (EditText) dialogView.findViewById(R.id.edName_Pro);
        EditText edPrice = (EditText) dialogView.findViewById(R.id.edPrice_Pro);
        EditText edDescription = (EditText) dialogView.findViewById(R.id.edDescription_Pro);
        EditText edCategory = (EditText) dialogView.findViewById(R.id.edCategory_Pro);

        edAvatar.setText(sanpham.getResourceImage());
        edName.setText(sanpham.getName());
        edPrice.setText(String.valueOf(sanpham.getPrice()));
        edDescription.setText(sanpham.getDescription());
        edCategory.setText(sanpham.getCategory());


        alertDialog.setPositiveButton("Đồng ý",(dialog,which)->{
            sanpham.setResourceImage(edAvatar.getText().toString());
            sanpham.setName(edName.getText().toString());
            sanpham.setPrice(Double.parseDouble(edPrice.getText().toString()));
            sanpham.setDescription(edDescription.getText().toString());
            sanpham.setCategory(edCategory.getText().toString());

            if(sanpham.resourceImage.isEmpty() || sanpham.name.isEmpty() || sanpham.description.isEmpty())
            {
                Toast.makeText(AdminActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            }
            else
            {
                int id = spDataQuery.update(AdminActivity.this, sanpham);
                if(id > 0)
                {
                    Toast.makeText(AdminActivity.this, "Cập nhật sản phẩm thành công", Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.create();
        alertDialog.show();
    }

    void resetData() {
        lstSP.clear();
        lstSP.addAll(spDataQuery.getAll(AdminActivity.this));
        spAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleteCLicked(sp sanpham, int position) {
        boolean rs = spDataQuery.delete(AdminActivity.this, sanpham.idPro);
        if(rs){
            Toast.makeText(this,"Xóa thành công",Toast.LENGTH_LONG).show();
            resetData();
        }
        else
        {
            Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemEditClicked(sp sanpham, int position) {
        updateUserDialog(sanpham);
    }
}