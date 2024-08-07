package com.example.doan.custom_textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class RobotoLightTV extends AppCompatTextView {
    public RobotoLightTV(@NonNull Context context) {
        super(context);
        setFrontsTextView();
    }

    public RobotoLightTV(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFrontsTextView();
    }

    public RobotoLightTV(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFrontsTextView();
    }

    private void setFrontsTextView()
    {
        Typeface typeface = Utils.getRoboto_Light(getContext());
        setTypeface(typeface);
    }
}
