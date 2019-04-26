package com.demo.makeview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.makeview.R;
import com.demo.makeview.utils.DisplayUtil;

import java.util.List;

/**
 * 描述:
 * 创建时间：2019/4/25-8:39 PM
 *
 * @author: yinshuai
 */
public class MakeTimeBar extends HorizontalScrollView {
    private Context mContext;

    private LinearLayout linearLayout;

    public MakeTimeBar(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public MakeTimeBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }


    public void initView() {
        //垂直方向的水平滚动条是否显示
        setVerticalScrollBarEnabled(false);
        //水平方向的水平滚动条是否显示
        setHorizontalScrollBarEnabled(false);
        linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(DisplayUtil.dip2px(mContext, 100), 0, 0, 0);
        addView(linearLayout);
    }

    public void setTimeData(List<String> timeData) {
        if (timeData != null) {
            for (int i = 0; i < timeData.size(); i++) {
                linearLayout.addView(new TimeViewItem(mContext, timeData.get(i)));
            }
        }
    }


    public class TimeViewItem extends RelativeLayout {

        private Context mContext;
        private TextView textView;
        private View leftLine;
        private View rightLine;
        private String time = "";


        public TimeViewItem(Context context, String time) {
            super(context);
            mContext = context;
            this.time = time;
            initView();
        }

        public TimeViewItem(Context context, AttributeSet attrs) {
            super(context, attrs);
            mContext = context;
            initView();
        }

        public void initView() {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_make_time, this);
            textView = view.findViewById(R.id.time);
            leftLine = view.findViewById(R.id.leftLine);
            rightLine = view.findViewById(R.id.rightLine);
            setTime();
        }


        public void setTime() {
            textView.setText(time);
        }
    }






}
