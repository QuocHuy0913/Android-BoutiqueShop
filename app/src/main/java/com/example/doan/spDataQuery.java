package com.example.doan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class spDataQuery {

    // Thêm mới 1 sản phẩm
    public static long  insert(Context context, sp sanpham)
    {
        spDBHelper userDBHelper = new spDBHelper(context);

        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utils.IMAGE_PRODUCT,sanpham.getResourceImage());
        values.put(Utils.NAME_PRODUCT,sanpham.getName());
        values.put(Utils.PRICE,sanpham.getPrice());
        values.put(Utils.DESCRIPTION_PRODUCT,sanpham.getDescription());
        values.put(Utils.CATEGORY_PRODUCT, sanpham.getCategory());

        long rs = sqLiteDatabase.insert(Utils.TABLE_PRODUCT,null,values);

        return (rs);
    }

    // Lấy danh sách
    public static ArrayList<sp> getAll(Context context)
    {
        ArrayList<sp> lstProduct = new ArrayList<>();

        spDBHelper spDBHelper = new spDBHelper(context);

        SQLiteDatabase db = spDBHelper.getReadableDatabase();

        Cursor cs = db.rawQuery("Select * from "+Utils.TABLE_PRODUCT,null);

        cs.moveToFirst();
        while (!cs.isAfterLast())
        {
            long idPro = cs.getLong(0);
            String avatar = cs.getString(1);
            String name = cs.getString(2);
            double price = cs.getDouble(3);
            String description = cs.getString(4);
            String category = cs.getString(5);
            long idUser = cs.getLong(6);


            lstProduct.add(new sp(idPro, avatar, name, price, description, category, idUser));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstProduct;
    }

        // Xóa
        public static boolean delete(Context context,long id)
        {
            spDBHelper spDBHelper = new spDBHelper(context);

            SQLiteDatabase sqLiteDatabase = spDBHelper.getWritableDatabase();

            int rs = sqLiteDatabase.delete(Utils.TABLE_PRODUCT,Utils.ID_PRODUCT+"=?",new String[]{String.valueOf(id)});
            return (rs>0);
        }

    // Cập nhật
    public static int update(Context context,sp sanpham)
    {
        spDBHelper spDBHelper = new spDBHelper(context);

        SQLiteDatabase sqLiteDatabase = spDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utils.IMAGE_PRODUCT,sanpham.getResourceImage());
        values.put(Utils.NAME_PRODUCT,sanpham.getName());
        values.put(Utils.PRICE,sanpham.getPrice());
        values.put(Utils.DESCRIPTION_PRODUCT,sanpham.getDescription());
        values.put(Utils.CATEGORY_PRODUCT, sanpham.getCategory());

        int rs = sqLiteDatabase.update(Utils.TABLE_PRODUCT,values,Utils.ID_PRODUCT+"=?",new String[]{String.valueOf(sanpham.idPro)});
        return (rs);
    }
}
