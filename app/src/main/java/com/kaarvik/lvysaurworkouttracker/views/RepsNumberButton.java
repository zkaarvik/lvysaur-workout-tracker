package com.kaarvik.lvysaurworkouttracker.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

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
}
