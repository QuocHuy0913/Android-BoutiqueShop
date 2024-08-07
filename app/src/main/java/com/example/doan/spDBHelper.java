package com.example.doan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.PrimitiveIterator;

public class spDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = Utils.DATABASE_NAME;
    private static final int DATABASE_VERSION = 2; // 3

    public spDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TAO_BANG_USER = "CREATE TABLE "+ Utils.TABLE_USER +" ( "
                +Utils.ID_USER+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                +Utils.NAME_USER+" TEXT, "
                +Utils.PASSWORD_USER+" TEXT, "
                +Utils.BIRTHDAY_USER+" TEXT, "
                +Utils.SEX_USER+" INTERGER, "
                +Utils.AVATAR+" TEXT, "
                +Utils.ROLE_USER+" TEXT);";
        sqLiteDatabase.execSQL(TAO_BANG_USER);

        String TAO_BANG_PRODUCTS = "CREATE TABLE "+ Utils.TABLE_PRODUCT +" ( "
                +Utils.ID_PRODUCT+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                +Utils.IMAGE_PRODUCT+" TEXT, "
                +Utils.NAME_PRODUCT+" TEXT, "
                +Utils.PRICE+" TEXT, "
                +Utils.DESCRIPTION_PRODUCT+" TEXT, "
                +Utils.CATEGORY_PRODUCT+" TEXT, "
                +Utils.IDUSER_PRODUCT+" TEXT, FOREIGN KEY ( "+ Utils.IDUSER_PRODUCT +" ) REFERENCES "+
                Utils.TABLE_USER+"("+Utils.ID_USER+"));";
        sqLiteDatabase.execSQL(TAO_BANG_PRODUCTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Utils.TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Utils.TABLE_PRODUCT);
        onCreate(sqLiteDatabase);
    }
}
