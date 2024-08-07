package com.example.doan.custom_textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class RobotoBoldTV extends AppCompatTextView {
    public RobotoBoldTV(@NonNull Context context) {
        super(context);
        setFrontsTextView();
    }

    public RobotoBoldTV(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFrontsTextView();
    }

    public RobotoBoldTV(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFrontsTextView();
    }

    private void setFrontsTextView()
    {
        Typeface typeface = Utils.getRoboto_bold(getContext());
        setTypeface(typeface);
    }
}
