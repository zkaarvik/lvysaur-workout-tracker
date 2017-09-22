package com.kaarvik.lvysaurworkouttracker.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.kaarvik.lvysaurworkouttracker.R;

/**
 *  Extend ElegantNumberButton to allow clicking the textview in the centre displaying the number
 */

public class RepsNumberButton extends ElegantNumberButton {
    private Context context;
    private TextView textView;
    private View view;
    private OnClickListener mClickListener;

    public RepsNumberButton(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public RepsNumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public RepsNumberButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        textView = (TextView) findViewById(com.cepheuen.elegantnumberbutton.R.id.number_counter);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mClickListener != null) {
                    mClickListener.onClick(v);
                }
            }
        });
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        this.mClickListener = onClickListener;
    }

    @Override
    public void setBackgroundColor(@ColorInt int color) {
        //super.setBackgroundColor(color);
        final Resources res = getResources();
        LinearLayout mLayout = (LinearLayout) findViewById(com.cepheuen.elegantnumberbutton.R.id.layout);
        Drawable drawable = res.getDrawable(com.cepheuen.elegantnumberbutton.R.drawable.background);

        drawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC));
        mLayout.setBackground(drawable);
    }

}
