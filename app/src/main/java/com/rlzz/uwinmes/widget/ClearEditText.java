package com.rlzz.uwinmes.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by monty on 2017/8/11.
 */

public class ClearEditText extends android.support.v7.widget.AppCompatEditText {
    public ClearEditText(Context context) {
        this(context,null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
}
