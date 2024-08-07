package com.example.doan.custom_textview;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {
    private static Typeface Roboto_black;
    private static Typeface Roboto_blackItalic;
    private static Typeface Roboto_bold;
    private static Typeface Roboto_boldItalic;
    private static Typeface Roboto_Light;
    private static Typeface Roboto_Medium;
    private static Typeface Roboto_Regular;

    public static Typeface getRoboto_black(Context context) {
        if(Roboto_black == null)
        {
            Roboto_black = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Black.ttf");
        }
        return Roboto_black;
    }

    public static Typeface getRoboto_blackItalic(Context context) {
        if(Roboto_blackItalic == null)
        {
            Roboto_blackItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-BlackItalic.ttf");
        }
        return Roboto_blackItalic;
    }

    public static Typeface getRoboto_bold(Context context) {
        if(Roboto_bold == null)
        {
            Roboto_bold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
        }
        return Roboto_bold;
    }

    public static Typeface getRoboto_boldItalic(Context context) {
        if(Roboto_boldItalic == null)
        {
            Roboto_boldItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-BoldItalic.ttf");
        }
        return Roboto_boldItalic;
    }

    public static Typeface getRoboto_Light(Context context) {
        if(Roboto_Light == null)
        {
            Roboto_Light = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
        }
        return Roboto_Light;
    }

    public static Typeface getRoboto_Medium(Context context) {
        if(Roboto_Medium == null)
        {
            Roboto_Medium = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
        }
        return Roboto_Medium;
    }

    public static Typeface getRoboto_Regular(Context context) {
        if(Roboto_Regular == null)
        {
            Roboto_Regular = Typeface.createFromAsset(context.getAssets(), "fonts/Regular.ttf");
        }
        return Roboto_Regular;
    }
}
