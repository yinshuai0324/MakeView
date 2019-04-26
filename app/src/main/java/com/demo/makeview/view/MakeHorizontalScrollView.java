package com.demo.makeview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * 描述:
 * 创建时间：2019/4/26-4:26 PM
 *
 * @author: yinshuai
 */
public class MakeHorizontalScrollView extends HorizontalScrollView {

    private OnMakeScrollChangeListener listener;

    public interface OnMakeScrollChangeListener {
        void onCustomScrollChange(MakeHorizontalScrollView listener, int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }

    public void setOnCustomScrollChangeListener(OnMakeScrollChangeListener listener) {
        this.listener = listener;
    }

    public MakeHorizontalScrollView(Context context) {
        this(context, null);
    }

    public MakeHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MakeHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (null != listener)
            listener.onCustomScrollChange(MakeHorizontalScrollView.this, l, t, oldl, oldt);
    }
}
