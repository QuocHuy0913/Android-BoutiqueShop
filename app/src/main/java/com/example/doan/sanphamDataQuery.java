package com.example.doan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class sanphamDataQuery {
    // Lấy danh sách
    public static ArrayList<Sanpham> getAll(Context context, String category)
    {
        ArrayList<Sanpham> lstProduct = new ArrayList<>();

        spDBHelper spDBHelper = new spDBHelper(context);

        SQLiteDatabase db = spDBHelper.getReadableDatabase();

        Cursor cs = db.rawQuery("Select * from "+Utils.TABLE_PRODUCT + " where " + Utils.CATEGORY_PRODUCT +"='"+ category+"'" ,null);

        cs.moveToFirst();
        while (!cs.isAfterLast())
        {
            long idPro = cs.getLong(0);
            String avatar = cs.getString(1);
            String name = cs.getString(2);
            double price = cs.getDouble(3);
            String description = cs.getString(4);
            category = cs.getString(5);
            long idUser = cs.getLong(6);


            lstProduct.add(new Sanpham(idPro, avatar, name, price, description, category, idUser));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstProduct;
    }

    public static ArrayList<Sanpham> getAll1(Context context, String search)
    {
        ArrayList<Sanpham> lstProduct = new ArrayList<>();

        spDBHelper spDBHelper = new spDBHelper(context);

        SQLiteDatabase db = spDBHelper.getReadableDatabase();

        Cursor cs = db.rawQuery("Select * from "+Utils.TABLE_PRODUCT + " where " + Utils.NAME_PRODUCT +"like'%"+ search+"%'" ,null);

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


            lstProduct.add(new Sanpham(idPro, avatar, name, price, description, category, idUser));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstProduct;
    }
}
