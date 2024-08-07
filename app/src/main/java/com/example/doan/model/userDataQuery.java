package com.example.doan.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doan.Utils;
import com.example.doan.sp;
import com.example.doan.spDBHelper;

import java.util.ArrayList;

public class userDataQuery {

    // Thêm mới 1 user
    public static long  insert(Context context, User user)
    {
        spDBHelper userDBHelper = new spDBHelper(context);

        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utils.NAME_USER,user.getUserName());
        values.put(Utils.PASSWORD_USER,user.getPassword());
        values.put(Utils.BIRTHDAY_USER,user.getBirthday());
        values.put(Utils.SEX_USER,user.getSex());
        values.put(Utils.AVATAR, user.getAvatar());
        values.put(Utils.ROLE_USER, user.getRole());

        long rs = sqLiteDatabase.insert(Utils.TABLE_USER,null,values);

        return (rs);
    }

    // Lấy danh sách
    public static ArrayList<User> getAll(Context context)
    {
        ArrayList<User> lstUser = new ArrayList<>();

        spDBHelper spDBHelper = new spDBHelper(context);

        SQLiteDatabase db = spDBHelper.getReadableDatabase();

        Cursor cs = db.rawQuery("Select * from "+Utils.TABLE_USER,null);

        cs.moveToFirst();
        while (!cs.isAfterLast())
        {
            long idUser = cs.getLong(0);
            String UserName = cs.getString(1);
            String Password = cs.getString(2);
            String BirthDay = cs.getString(3);
            int Sex = cs.getInt(4);
            String Avatar = cs.getString(5);
            String Role = cs.getString(6);


            lstUser.add(new User(idUser, UserName, Password, BirthDay, Sex, Avatar, Role));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstUser;
    }

    // Xóa
    public static boolean delete(Context context,long id)
    {
        spDBHelper spDBHelper = new spDBHelper(context);

        SQLiteDatabase sqLiteDatabase = spDBHelper.getWritableDatabase();

        int rs = sqLiteDatabase.delete(Utils.TABLE_USER,Utils.ID_USER+"=?",new String[]{String.valueOf(id)});
        return (rs>0);
    }

    // Cập nhật
    public static int update(Context context, User user)
    {
        spDBHelper spDBHelper = new spDBHelper(context);

        SQLiteDatabase sqLiteDatabase = spDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utils.NAME_USER,user.getUserName());
        values.put(Utils.PASSWORD_USER,user.getPassword());
        values.put(Utils.BIRTHDAY_USER,user.getBirthday());
        values.put(Utils.SEX_USER,user.getSex());
        values.put(Utils.AVATAR, user.getAvatar());
        values.put(Utils.ROLE_USER, user.getRole());

        int rs = sqLiteDatabase.update(Utils.TABLE_USER,values,Utils.ID_USER+"=?",new String[]{String.valueOf(user.idUser)});
        return (rs);
    }
}
