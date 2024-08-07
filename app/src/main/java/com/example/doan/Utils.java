package com.example.doan;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static final String DATABASE_NAME= "app_ban_quan_ao";

    // TABLE_USER
    public static final String TABLE_USER = "[User]";
    public static final String ID_USER = "[IDUser]";
    public static final String NAME_USER = "[NameUser]";
    public static final String PASSWORD_USER = "[PasswordUser]";
    public static final String BIRTHDAY_USER = "[BirthdayUser]";
    public static final String SEX_USER = "[SexUser]";
    public static final String AVATAR = "[Avartar]";
    public static final String ROLE_USER = "[RoleUser]";
    // TABLE_PRODUCT
    public static final String TABLE_PRODUCT = "[Products]";
    public static final String ID_PRODUCT = "[ProductID]";
    public static final String IMAGE_PRODUCT = "[Image]";
    public static final String NAME_PRODUCT = "[NamePro]";
    public static final String PRICE = "[Price]";
    public static final String DESCRIPTION_PRODUCT = "[DescriptionPro]";
    public static final String CATEGORY_PRODUCT = "[CategoryPro]";
    public static final String IDUSER_PRODUCT = "[UserID]";

    public static Bitmap convertToBitmapFromAssets(Context context, String nameImage)
    {
        AssetManager assetManager = context.getAssets();
        try
        {
            InputStream inputStream = assetManager.open("aaaaa/" + nameImage);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
